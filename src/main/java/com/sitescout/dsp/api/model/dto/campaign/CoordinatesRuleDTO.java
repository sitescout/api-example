package com.sitescout.dsp.api.model.dto.campaign;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class CoordinatesRuleDTO {
    public enum DistanceUnit {
        METERS, FEET;

        @JsonValue
        public String toJsonString() {
            return this.name().toLowerCase();
        }

        @JsonCreator
        public static DistanceUnit fromJsonString(String valueStr) {
            return valueStr == null ? null : valueOf(valueStr.toUpperCase());
        }
    }

    private Double latitude;
    private Double longitude;
    private Integer distance;
    private DistanceUnit unit;
    private Boolean blocked;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public DistanceUnit getUnit() {
        return unit;
    }

    public void setUnit(DistanceUnit unit) {
        this.unit = unit;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoordinatesRuleDTO that = (CoordinatesRuleDTO) o;

        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (unit != that.unit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = latitude != null ? latitude.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
