package com.sitescout.dsp.api.util.io.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sitescout.dsp.api.util.DateFormatters;
import org.joda.time.LocalTime;

import java.io.IOException;

public class JodaTimeJsonDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            String text = jsonParser.getText();
            try {
                return DateFormatters.TIME_FORMATTER.parseLocalTime(text);
            } catch (IllegalArgumentException e) {
                throw deserializationContext.mappingException("Cannot parse time from " + text);
            }
        }

        throw deserializationContext.mappingException("Expected " + DateFormatters.TIME_FORMAT);
    }
}
