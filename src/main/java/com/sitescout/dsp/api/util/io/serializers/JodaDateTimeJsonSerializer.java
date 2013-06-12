package com.sitescout.dsp.api.util.io.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sitescout.dsp.api.util.DateFormatters;
import org.joda.time.DateTime;

import java.io.IOException;

public class JodaDateTimeJsonSerializer extends JsonSerializer<DateTime> {
    @Override
    public void serialize(DateTime date, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(DateFormatters.DATE_TIME_FORMATTER.print(date));
    }
}
