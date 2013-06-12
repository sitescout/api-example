package com.sitescout.dsp.api.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public enum AdFormat {
    image,
    flash,
    expandable,
    text,
    html,
    video;

    private final static Logger logger = LoggerFactory.getLogger(AdFormat.class);

    public static List<AdFormat> fromString(String str) {
        if (str != null) {
            str = str.trim();
            if (str.startsWith("[") && str.endsWith("]") && str.length() > 2) {
                String[] formatsStr = str.substring(1, str.length() - 1).split(",");
                List<AdFormat> formats = new ArrayList<AdFormat>();
                for (String formatStr : formatsStr) {
                    try {
                        formats.add(AdFormat.valueOf(formatStr.trim()));
                    } catch (IllegalArgumentException e) {
                        logger.error("Unknown ad format ({}) found", formatStr);
                    }
                }
                return formats;
            }
        }
        return new ArrayList<AdFormat>();
    }
}
