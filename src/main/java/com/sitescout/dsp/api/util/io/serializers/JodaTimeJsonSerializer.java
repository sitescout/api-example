package com.sitescout.dsp.api.util.io.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sitescout.dsp.api.util.DateFormatters;
import org.joda.time.LocalTime;

import java.io.IOException;

public class JodaTimeJsonSerializer extends JsonSerializer<LocalTime> {
    @Override
    public void serialize(LocalTime time, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(DateFormatters.TIME_FORMATTER.print(time));
    }
}
