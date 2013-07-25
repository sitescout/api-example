package com.sitescout.ui;

import com.sitescout.ui.qualifiers.Advertiser;
import com.sitescout.ui.qualifiers.Key;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * The advertiser key for the current user.
 *
 * @author sean
 */
@Named
@SessionScoped
public class AdvertiserKeyProducer implements Serializable {

    Integer advertiserKey;

    @Inject Event<AdvertiserKeyChangeEvent> advertiserKeyChangeEventEvent;

    @Produces @Named @Advertiser @Key
    public Integer getAdvertiserKey() {
        return advertiserKey;
    }

    public void setAdvertiserKey(Integer advertiserKey) {
        this.advertiserKey = advertiserKey;
        advertiserKeyChangeEventEvent.fire(new AdvertiserKeyChangeEvent());
    }

    public String redirect() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
        String fullURI = servletRequest.getRequestURI();
        String[] split = fullURI.split("/");

        //fullURI is either /api-example/index.jsf
        //or api-example/campaigns/******.jsf (campaigns or audience or control)

        if (split.length > 3) {
            return "/" + split[2] + "/" + split[2] + ".jsf?faces-redirect=true";
        }
        return split[2] + "?faces-redirect=true";
    }

    public class AdvertiserKeyChangeEvent {

    }
}
