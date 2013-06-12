package com.sitescout.dsp.api.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.net.URI;

public class Link {
    public enum LinkRel {
        SELF,
        NEXT,
        PREV,
        STATS,
        STATS_PER_SITE,
        STATS_PER_CREATIVE,
        STATS_PER_OFFER,
        STATS_PER_PLACEMENT,
        TAG,
        DENIED_ATTRIBUTES,
        SITE;

        @JsonValue
        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public enum RequestMethod {
        GET,
        POST,
        PUT,
        DELETE
    }

    private URI href;
    private String rel;
    private RequestMethod method;

    public Link() {
    }

    public Link(URI href, String rel) {
        this(href, rel, null);
    }

    public Link(URI href, String rel, RequestMethod method) {
        this.href = href;
        this.rel = rel;
        this.method = method;
    }

    public Link(URI href, LinkRel rel) {
        this(href, rel, null);
    }

    public Link(URI href, LinkRel rel, RequestMethod method) {
        this(href, rel != null ? rel.toString() : null, method);
    }

    public URI getHref() {
        return href;
    }

    public void setHref(URI href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }
}
