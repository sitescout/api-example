package com.sitescout.dsp.api.model.dto.campaign;

import com.sitescout.dsp.api.model.dto.LinkedDTO;
import com.sitescout.dsp.api.type.AssetReviewStatus;
import com.sitescout.dsp.api.type.AssetStatus;
import com.sitescout.dsp.api.type.PagePosition;
import com.sitescout.dsp.api.util.csv.CsvProperties;

@CsvProperties({"siteRef", "domain", "status", "reviewStatus"})
public class SiteRuleDTO extends LinkedDTO {
    public static final double MAX_BID = 100;

    private Integer ruleId;
    private String siteRef;
    private AssetStatus status;
    private AssetReviewStatus reviewStatus;
    private String dimensions;
    private PagePosition pagePosition;
    private String domain;
    private Double bid;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getSiteRef() {
        return siteRef;
    }

    public void setSiteRef(String siteRef) {
        this.siteRef = siteRef;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public AssetReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(AssetReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public PagePosition getPagePosition() {
        return pagePosition;
    }

    public void setPagePosition(PagePosition pagePosition) {
        this.pagePosition = pagePosition;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean matchesFilter(String filter) {
        if (domain != null && domain.contains(filter)) {
            return true;
        } else if (siteRef != null && siteRef.contains(filter)) {
            return true;
        } else {
            return false;
        }
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteRuleDTO that = (SiteRuleDTO) o;

        if (dimensions != null ? !dimensions.equals(that.dimensions) : that.dimensions != null) return false;
        if (siteRef != null ? !siteRef.equals(that.siteRef) : that.siteRef != null) return false;
        PagePosition normalizedPagePosition = pagePosition == PagePosition.unknown ? null : pagePosition;
        PagePosition normalizedThatPagePosition = that.pagePosition == PagePosition.unknown ? null : that.pagePosition;
        if (normalizedPagePosition != normalizedThatPagePosition) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = siteRef != null ? siteRef.hashCode() : 0;
        result = 31 * result + (dimensions != null ? dimensions.hashCode() : 0);
        result = 31 * result + (pagePosition != null ? pagePosition.hashCode() : 0);
        return result;
    }
}
