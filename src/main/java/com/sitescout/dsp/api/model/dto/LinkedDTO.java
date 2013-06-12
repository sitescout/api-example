package com.sitescout.dsp.api.model.dto;

import com.sitescout.dsp.api.model.Link;

import java.util.ArrayList;
import java.util.List;

public abstract class LinkedDTO {
    private List<Link> links;

    public LinkedDTO() {
        this.links = new ArrayList<Link>();
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(Link link) {
        if (getLink(link.getRel()) == null) {
            links.add(link);
        }
    }

    public Link getLink(Link.LinkRel rel) {
        return getLink(rel.toString());
    }

    public Link getLink(String rel) {
        for (Link link : links) {
            if (link.getRel().equals(rel)) {
                return link;
            }
        }
        return null;
    }
}
