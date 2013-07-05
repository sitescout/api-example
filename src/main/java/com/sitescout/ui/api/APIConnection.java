package com.sitescout.ui.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitescout.ui.AdvertiserKeyProducer;
import com.sitescout.ui.page.Login;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all direct interactions with the remote API.
 *
 * @author sean
 */
@SessionScoped
public class APIConnection implements Serializable {

    @Inject private Logger log;
    @Inject Login login;
    @Inject AdvertiserKeyProducer advertiserKeyProducer;

    static final String AUTHORIZATION_URL = "https://api.sitescout.com/oauth/token";

    DefaultHttpClient client;

    APIConnection() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(
                new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(
                new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // Increase max connections for localhost:80 to 50
        HttpHost localhost = new HttpHost("locahost", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);


        client = new DefaultHttpClient(cm);
        client.setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                // otherwise keep alive for 30 seconds
                return 30 * 1000;
            }

        });
    }

    private String accessToken = null;

    String getAccessToken() {
        return accessToken;
    }

    public String authorize() {
        long startTime = System.currentTimeMillis();
        try {
            log.trace(this.toString());
            String credentials = login.getClientId() + ":" + login.getClientSecret();
            credentials = "Basic " + Base64.encodeBase64String(credentials.getBytes());

            HttpPost post = new HttpPost(AUTHORIZATION_URL);
            post.addHeader("Authorization", credentials);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");
            post.addHeader("Accept", "application/json");

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("grant_type", "client_credentials"));
            params.add(new BasicNameValuePair("scope", "stats"));
            params.add(new BasicNameValuePair("expect-continue", "true"));
            post.setEntity(new UrlEncodedFormEntity(params));
            long startTimeHTTP = System.currentTimeMillis();
            HttpResponse httpResponse = client.execute(post);
            log.debug("Authorization HTTP time: " + (System.currentTimeMillis() - startTimeHTTP));

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String response = rd.readLine();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            if (httpResponse.getStatusLine().getStatusCode() > 399) {
                throw new APIResponseException("An exception occurred during authorization.", response);
            }


            String accessToken = rootNode.get("access_token").toString();
            this.accessToken = accessToken.replace("\"", "");
        } catch (Exception e) {
            throw new RuntimeException("Could not authorize.", e);
        }
        log.debug("Authorization time: " + (System.currentTimeMillis() - startTime));
        return "success";
    }


    public JsonParser getData(String url) throws IOException {
        if (accessToken == null) {
            authorize();
        }
        long startTime = System.currentTimeMillis();
        HttpGet get = new HttpGet(url);
        get.addHeader("Authorization", "Bearer " + accessToken);

        long startTimeHTTP = System.currentTimeMillis();
        HttpResponse httpResponse = client.execute(get);
        log.debug("Fetch HTTP time: " + (System.currentTimeMillis() - startTimeHTTP));

        BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        String response = rd.readLine();


        if (httpResponse.getStatusLine().getStatusCode() == 403) {
            advertiserKeyProducer.setAdvertiserKey(null);
            throw new NoAccessException();
        } else if (httpResponse.getStatusLine().getStatusCode() > 399) {
            throw new APIResponseException("An exception occurred during authorization.", response);
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response);
        JsonParser jp = mapper.treeAsTokens(rootNode);
        log.debug("Fetch time: " + (System.currentTimeMillis() - startTime));
        return jp;

    }

    /**
     * For debugging purposes, dump the raw json returned by the given call.
     */
    public void printRaw(String link) throws IOException {
        getData(link).toString();

    }
}
