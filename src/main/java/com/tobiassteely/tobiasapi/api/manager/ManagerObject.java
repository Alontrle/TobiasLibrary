package com.tobiassteely.tobiasapi.api.manager;

import com.tobiassteely.tobiasapi.api.TobiasObject;
import com.tobiassteely.tobiasapi.config.Config;
import org.json.simple.JSONObject;

public class ManagerObject extends TobiasObject implements ManagerObjectInterface {

    private String key;

    public ManagerObject(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", key);
        return jsonObject;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
