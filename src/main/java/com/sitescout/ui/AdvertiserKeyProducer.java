package com.sitescout.ui;

import com.sitescout.ui.qualifiers.Advertiser;
import com.sitescout.ui.qualifiers.Key;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
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

    public class AdvertiserKeyChangeEvent {

    }
}
