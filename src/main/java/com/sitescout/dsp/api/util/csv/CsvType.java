package com.sitescout.dsp.api.util.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvType {
    enum Value {
        INHERIT, NUMBER, CURRENCY, PERCENT
    }

    Value value() default Value.INHERIT;
}
