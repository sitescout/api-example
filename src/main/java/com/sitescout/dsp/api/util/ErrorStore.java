package com.sitescout.dsp.api.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ErrorStore {
    public static final String PARAM_VALIDATION_ERROR_CODE = "SV00001";
    public static final String JSON_FIELD_VALIDATION_ERROR_CODE = "SV00004";
    public static final String ENTITY_NOT_FOUND_ERROR_CODE = "SE00004";

    private static final Map<String, String> ERRORS;

    static {
        Map<String, String> tempMap = new HashMap<String, String>();
        tempMap.put("SV00001", "Parameter \"%s\" is invalid: %s");
        tempMap.put("SV00002", "End date (%s) must be after the start date (%s)");
        tempMap.put("SV00003", "Page size (%s) must be less or equal to %s");
        tempMap.put("SV00004", "JSON field \"%s\" is invalid");
        tempMap.put("HB00001", "Entities could not be loaded");
        tempMap.put("DB00001", "Stats could not be loaded");
        tempMap.put("SE00000", "Unspecified internal error occurred. Assigned ID is %s");
        tempMap.put("SE00001", "Unknown sorting field received");
        tempMap.put("SE00002", "JSON processing error. Assigned ID is %s");
        tempMap.put("SE00003", "Creative %s could not be found in campaign %s");
        tempMap.put("SE00004", "Entity of type %s with ID %s could not be found");
        tempMap.put("AE00000", "Access token required");
        tempMap.put("AE00001", "Invalid access token provided");
        tempMap.put("AE00002", "Access token has expired");
        tempMap.put("AE00003", "Unauthorized to access this resource");
        ERRORS = Collections.unmodifiableMap(tempMap);
    }

    public static String getMessage(String errorCode, String[] arguments) {
        String message = ERRORS.get(errorCode);
        if (message != null) {
            return String.format(message, (Object[])arguments);
        }
        return "Unknown error";
    }
}
