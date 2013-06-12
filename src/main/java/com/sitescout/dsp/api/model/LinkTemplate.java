package com.sitescout.dsp.api.model;

public class LinkTemplate {
    private String template;
    private String rel;
    private Link.RequestMethod method;

    public LinkTemplate(String template, Link.LinkRel rel) {
        this(template, rel, null);
    }

    public LinkTemplate(String template, Link.LinkRel rel, Link.RequestMethod method) {
        this(template, rel != null ? rel.toString() : null, method);
    }

    public LinkTemplate(String template, String rel) {
        this(template, rel, null);
    }

    public LinkTemplate(String template, String rel, Link.RequestMethod method) {
        this.template = template;
        this.rel = rel;
        this.method = method;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public Link.RequestMethod getMethod() {
        return method;
    }

    public void setMethod(Link.RequestMethod method) {
        this.method = method;
    }
}
