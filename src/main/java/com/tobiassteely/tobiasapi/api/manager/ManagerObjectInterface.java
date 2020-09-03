package com.tobiassteely.tobiasapi.api.manager;

import org.json.simple.JSONObject;

public interface ManagerObjectInterface {

    String getKey();

    void setKey(String key);

    JSONObject toJson();

}
