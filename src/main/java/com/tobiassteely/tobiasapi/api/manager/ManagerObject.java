package com.tobiassteely.tobiasapi.api.manager;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.TobiasObject;

public class ManagerObject extends TobiasObject {

    private String key;

    public ManagerObject(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
