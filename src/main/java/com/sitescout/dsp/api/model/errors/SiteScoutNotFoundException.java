package com.sitescout.dsp.api.model.errors;

public class SiteScoutNotFoundException extends SiteScoutServiceException {
    public SiteScoutNotFoundException(String errorCode, String... arguments) {
        super(errorCode, arguments);
    }
}
