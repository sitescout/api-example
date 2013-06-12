package com.sitescout.dsp.api.type;

import java.util.EnumSet;
import java.util.Set;

public enum InventoryType {
    web(EnumSet.of(TrafficType.WEB, TrafficType.EMAIL)),
    mobile_web(EnumSet.of(TrafficType.MOBILE_WEB, TrafficType.MOBILE_OPTIMIZED_WEB)),
    app(EnumSet.of(TrafficType.MOBILE_APP)),
    all_mobile(EnumSet.of(TrafficType.MOBILE_APP, TrafficType.MOBILE_WEB, TrafficType.MOBILE_OPTIMIZED_WEB)),
    all_web(EnumSet.complementOf(EnumSet.of(TrafficType.MOBILE_APP))),
    all(EnumSet.allOf(TrafficType.class));

    private Set<TrafficType> trafficTypes;

    private InventoryType(EnumSet trafficTypes) {
        this.trafficTypes = trafficTypes;
    }

    public boolean matches(TrafficType trafficType) {
        return trafficTypes.contains(trafficType);
    }
}
