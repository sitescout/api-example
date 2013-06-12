package com.sitescout.dsp.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sitescout.dsp.api.model.params.Dimensions;
import com.sitescout.dsp.api.type.*;
import com.sitescout.dsp.api.util.csv.CsvProperties;

/**
 * CreativeDTO is a transfer object for all types of creatives and CampaignAd. Currently, a logical hierarchy of
 * creative types is flattened into this class--this is the premise and purpose of domain transfer objects.
 */
@CsvProperties({"creativeId", "width", "height", "type"})
public class CreativeDTO extends LinkedDTO {
    public static final int MAX_LENGTH_LABEL = 60;
    public static final int MAX_LENGTH_VAULT_PATH = 255;

    private Integer creativeId;
    private String label;
    private Integer width;
    private Integer height;
    private CreativeType type;
    private AssetReviewStatus reviewStatus;
    private String previewUrl;
    private String vaultPath;

    // Banners
    private String assetUrl;

    // Tag Ads
    private String code;

    // Expandables
    private RichMediaVendor vendor;
    private ExpandingDirection expandingDirection;

    // Campaign Creatives
    private AssetStatus status;

    // Use type instead, this for backward-compatibility only
    @Deprecated
    private AdFormat format;

    public Integer getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Integer creativeId) {
        this.creativeId = creativeId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public AdFormat getFormat() {
        return format;
    }

    public void setFormat(AdFormat format) {
        this.format = format;
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

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public boolean matchesDimensions(Dimensions dimensions) {
        return width == dimensions.getWidth() && height == dimensions.getHeight();
    }

    public CreativeType getType() {
        return type;
    }

    public void setType(CreativeType type) {
        this.type = type;
    }

    public String getVaultPath() {
        return vaultPath;
    }

    public void setVaultPath(String vaultPath) {
        this.vaultPath = vaultPath;
    }

    public String getAssetUrl() {
        return assetUrl;
    }

    public void setAssetUrl(String assetUrl) {
        this.assetUrl = assetUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RichMediaVendor getVendor() {
        return vendor;
    }

    public void setVendor(RichMediaVendor vendor) {
        this.vendor = vendor;
    }

    public ExpandingDirection getExpandingDirection() {
        return expandingDirection;
    }

    public void setExpandingDirection(ExpandingDirection expandingDirection) {
        this.expandingDirection = expandingDirection;
    }

    @JsonIgnore
    public String getDimensions() {
        return Dimensions.buildDimensionsString(width, height);
    }
}
