package com.sitescout.dsp.api.model.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Error {
    private String errorCode;
    @JsonIgnore
    private String[] arguments;
    private String message;

    public Error(String errorCode, String... arguments) {
        this.errorCode = errorCode;
        this.arguments = arguments;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] params) {
        this.arguments = params;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
