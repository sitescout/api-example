package com.sitescout.ui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitescout.dsp.api.model.errors.APIErrorResponse;

import java.io.IOException;

/**
 * Indicates that an error was received from the API.
 *
 * @author sean
 */
public class APIResponseException extends RuntimeException {

    private final APIErrorResponse response;

    public APIResponseException(String message, String response) throws IOException {
        super(message + response);
        ObjectMapper mapper = new ObjectMapper();
        this.response = mapper.readValue(response, APIErrorResponse.class);
    }

    public APIErrorResponse getResponse() {
        return response;
    }
}
