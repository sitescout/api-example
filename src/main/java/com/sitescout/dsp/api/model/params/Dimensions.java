package com.sitescout.dsp.api.model.params;

public class Dimensions {
    private Integer width;
    private Integer height;

    public Dimensions(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return buildDimensionsString(width, height);
    }

    public static String buildDimensionsString(Integer width, Integer height) {
        return width + "x" + height;
    }
}
