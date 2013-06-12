package com.sitescout.dsp.api.util;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sitescout.dsp.api.model.Views;

public class JacksonJsonDefaults {
    public static final Class<?> DEFAULT_VIEW = Views.Basic.class;
    private static final String[] DEFAULT_FILTERS = {"statsFilter"};
    public static final FilterProvider DEFAULT_FILTER_PROVIDER;

    static {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        for (String filter : DEFAULT_FILTERS) {
            filterProvider.addFilter(filter, SimpleBeanPropertyFilter.serializeAllExcept());
        }
        DEFAULT_FILTER_PROVIDER = filterProvider;
    }
}
