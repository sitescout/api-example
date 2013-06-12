package com.sitescout.ui;

import com.sitescout.ui.qualifiers.Advertiser;
import com.sitescout.ui.qualifiers.Key;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

/**
 * The advertiser key for the current user.
 *
 * @author sean
 */
@SessionScoped
public class AdvertiserKeyProducer implements Serializable {

    @Produces
    @Named
    @Advertiser
    @Key
    public int getAdvertiserKey() {
        return -1;
    }
}
