package com.sitescout.dsp.api.model.errors;

public class SiteScoutConflictException extends SiteScoutServiceException {
    public SiteScoutConflictException(String errorCode, String... arguments) {
        super(errorCode, arguments);
    }
}
