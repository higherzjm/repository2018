package com.j2ee.redis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MessageVo implements Serializable {
    private String key;
    private String date;
    private Map<String,List<String>>  websitematids;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, List<String>> getWebsitematids() {
        return websitematids;
    }

    public void setWebsitematids(Map<String, List<String>> websitematids) {
        this.websitematids = websitematids;
    }
}