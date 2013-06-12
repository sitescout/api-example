package com.sitescout.dsp.api.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateFormatters {
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String DATE_TIME_FORMAT = "yyyyMMdd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATE_TIME_FORMAT);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern(TIME_FORMAT);
}
