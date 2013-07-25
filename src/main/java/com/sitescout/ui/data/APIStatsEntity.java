package com.sitescout.ui.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.sitescout.ui.api.APIConnection;
import com.sitescout.ui.page.CalendarBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A base class for stats entities which fetch data through the API.
 *
 * @author sean
 */
@Named
@ViewScoped
public abstract class APIStatsEntity implements Serializable {
    @Inject CalendarBean calendarBean;
    @Inject Cache cache;
    @Inject APIConnection apiConnection;
    @Inject private Logger log;

    protected Object getDetailsVarargs(String siteRef, Integer... keys) {
        //named weirdly do avoid EL resolution issues

        String url = getLink(siteRef, keys) + "?" + getQueryString(getQueryParams());
        //if we don't yet have the details, fetch them
        if (!cache.dataCache.containsKey(url)) {
            log.trace("Fetching {}", url);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JodaModule());
                JsonParser dataReceived = apiConnection.getData(url);
                cache.dataCache.put(url, mapper.readValue(dataReceived, getEntityTypeReference()));
            } catch (IOException e) {
                log.error("Could not fetch API data from {}", url);

                try {
                    apiConnection.printRaw(url);
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
                throw new RuntimeException(e);
            }
        }
        return cache.dataCache.get(url);
    }

    protected abstract String getLink(String siteRef, Integer... key);

    protected abstract TypeReference getEntityTypeReference();

    protected Map<String, String> getQueryParams() {
        Map<String, String> querymap = new HashMap<>();
        if (calendarBean.getDateFrom() != null) {
            querymap.put("dateFrom", formatAPIDate(calendarBean.getDateFrom()));
        }
        if (calendarBean.getDateTo() != null) {
            querymap.put("dateTo", formatAPIDate(calendarBean.getDateTo()));
        }
        return querymap;
    }

    protected String getQueryString(Map<String, String> params) {
        List<String> values = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            values.add(entry.getKey() + "=" + entry.getValue());
        }
        return StringUtils.join(values, "&");
    }


    SimpleDateFormat apiDateFormatter = new SimpleDateFormat("yyyyMMdd");

    String formatAPIDate(Date date) {
        return apiDateFormatter.format(date);
    }
}

