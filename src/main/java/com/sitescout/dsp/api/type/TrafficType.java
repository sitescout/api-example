package com.sitescout.dsp.api.type;

public enum TrafficType {
    WEB("web", SiteType.SITE),
    MOBILE_WEB("mweb", SiteType.SITE),
    MOBILE_OPTIMIZED_WEB("mwebo", SiteType.SITE),
    MOBILE_APP("mapp", SiteType.APP),
    EMAIL("email", SiteType.EMAIL);

    private String statName;
    private SiteType siteType;

    TrafficType(String statName, SiteType siteType) {
        this.statName = statName;
        this.siteType = siteType;
    }

    public String getStatName() {
        return statName;
    }

    public SiteType getSiteType() {
        return siteType;
    }

    public static TrafficType fromStatName(String statName) {
        for (TrafficType t : TrafficType.values()) {
            if (t.getStatName().equals(statName)) {
                return t;
            }
        }
        return null;
    }
}
