package com.sitescout.dsp.api.util.io.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sitescout.dsp.api.util.DateFormatters;
import org.joda.time.DateTime;

import java.io.IOException;

public class JodaDateJsonDeserializer extends JsonDeserializer<DateTime> {
    @Override
    public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            return DateFormatters.DATE_FORMATTER.parseDateTime(jsonParser.getText());
        }

        throw deserializationContext.mappingException("Expected " + DateFormatters.DATE_FORMAT);
    }
}
