package com.sitescout.dsp.api.model.dto.campaign;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class AudienceRuleDTO {
    public enum AccessType {
        FIRST_ACCESS, LAST_ACCESS;

        @JsonValue
        public String toJsonString() {
            return this.name().toLowerCase();
        }

        @JsonCreator
        public static AccessType fromJsonString(String valueStr) {
            return valueStr == null ? null : valueOf(valueStr.toUpperCase());
        }
    }

    private Integer audienceId;
    private Boolean blocked;
    private AccessType accessType;
    private Integer daysAfterAccess;

    public Integer getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(Integer audienceId) {
        this.audienceId = audienceId;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    public Integer getDaysAfterAccess() {
        return daysAfterAccess;
    }

    public void setDaysAfterAccess(Integer daysAfterAccess) {
        this.daysAfterAccess = daysAfterAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AudienceRuleDTO that = (AudienceRuleDTO) o;

        if (accessType != that.accessType) return false;
        if (audienceId != null ? !audienceId.equals(that.audienceId) : that.audienceId != null) return false;
        if (daysAfterAccess != null ? !daysAfterAccess.equals(that.daysAfterAccess) : that.daysAfterAccess != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = audienceId != null ? audienceId.hashCode() : 0;
        result = 31 * result + (accessType != null ? accessType.hashCode() : 0);
        result = 31 * result + (daysAfterAccess != null ? daysAfterAccess.hashCode() : 0);
        return result;
    }
}
