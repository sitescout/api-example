package com.sitescout.dsp.api.model.errors;

public class SiteScoutServiceException extends Exception {
    private static final long serialVersionUID = 7611706763213416545L;

    private Error error;

    public SiteScoutServiceException(String errorCode, String... arguments) {
        this.error = new Error(errorCode, arguments);
    }

    public Error getError() {
        return error;
    }
}
