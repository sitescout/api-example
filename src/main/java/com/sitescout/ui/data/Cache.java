package com.sitescout.ui.data;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A general-purpose cache for use in
 */
@Named
@ViewScoped
public class Cache implements Serializable {
    Map<String, Object> dataCache = new HashMap<>();

    public Map<String, Object> getDataCache() {
        return dataCache;
    }

}
