package com.sitescout.dsp.api.model.params;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sitescout.dsp.api.util.io.serializers.JodaDateJsonDeserializer;
import com.sitescout.dsp.api.util.io.serializers.JodaDateJsonSerializer;
import org.joda.time.DateTime;

public class DateRange {
    @JsonSerialize(using = JodaDateJsonSerializer.class)
    @JsonDeserialize(using = JodaDateJsonDeserializer.class)
    private DateTime from;
    @JsonSerialize(using = JodaDateJsonSerializer.class)
    @JsonDeserialize(using = JodaDateJsonDeserializer.class)
    private DateTime to;

    public DateRange() {

    }

    public DateRange(DateTime from, DateTime to) {
        this.from = from;
        this.to = to;
    }

    public DateTime getFrom() {
        return from;
    }

    public void setFrom(DateTime from) {
        this.from = from;
    }

    public DateTime getTo() {
        return to;
    }

    public void setTo(DateTime to) {
        this.to = to;
    }
}
