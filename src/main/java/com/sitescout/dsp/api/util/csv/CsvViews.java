package com.sitescout.dsp.api.util.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvViews {
    enum Value {
        DEFAULT,
        ADVERTISER,
        ADMIN;
    }

    Value[] value() default Value.DEFAULT;
}
