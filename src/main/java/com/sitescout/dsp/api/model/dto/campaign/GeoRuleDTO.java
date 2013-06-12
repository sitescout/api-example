package com.sitescout.dsp.api.model.dto.campaign;

public class GeoRuleDTO {
    private String countryCode;
    private String region;
    private String city;
    private String postalCode;
    private Integer dmaCode;
    private Integer areaCode;

    private Boolean blocked;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getDmaCode() {
        return dmaCode;
    }

    public void setDmaCode(Integer dmaCode) {
        this.dmaCode = dmaCode;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
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

        GeoRuleDTO that = (GeoRuleDTO) o;

        if (areaCode != null ? !areaCode.equals(that.areaCode) : that.areaCode != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (dmaCode != null ? !dmaCode.equals(that.dmaCode) : that.dmaCode != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countryCode != null ? countryCode.hashCode() : 0;
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (dmaCode != null ? dmaCode.hashCode() : 0);
        result = 31 * result + (areaCode != null ? areaCode.hashCode() : 0);
        return result;
    }
}
