package com.sitescout.dsp.api.model;

public class Views {
    public static class Basic {
    }

    public static class StatsViews {
        public static class AdQuality extends Basic {
        }

        public static class Advertiser extends AdQuality {
        }

        public static class Admin extends Advertiser {
        }
    }

    public static class EntityViews {
        public static class Advertiser extends Basic {
        }
    }
}
