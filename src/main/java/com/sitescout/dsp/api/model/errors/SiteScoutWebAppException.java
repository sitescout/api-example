package com.sitescout.dsp.api.model.errors;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class SiteScoutWebAppException extends WebApplicationException {
    private static final long serialVersionUID = 6990612164246143007L;

    private Error error;

    public SiteScoutWebAppException(Response.Status status, SiteScoutServiceException serviceException) {
        this(Response.status(status).build(), serviceException.getError());
    }

    public SiteScoutWebAppException(Response response, SiteScoutServiceException serviceException) {
        this(response, serviceException.getError());
    }

    public SiteScoutWebAppException(Response.Status status, String errorCode, String... arguments) {
        this(Response.status(status).build(), errorCode, arguments);
    }

    public SiteScoutWebAppException(Response response, String errorCode, String... arguments) {
        this(response, new Error(errorCode, arguments));
    }

    private SiteScoutWebAppException(Response response, Error error) {
        super(response);
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}