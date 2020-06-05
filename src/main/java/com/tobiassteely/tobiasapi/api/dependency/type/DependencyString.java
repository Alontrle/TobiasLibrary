package com.tobiassteely.tobiasapi.api.dependency.type;

import com.tobiassteely.tobiasapi.api.dependency.DependencyObject;
import org.json.simple.JSONObject;

public class DependencyString implements DependencyObject {

    private String string;

    public DependencyString(String string) {
        this.string = string;
    }

    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        json.put("data", string);
        json.put("type", data_type_string);
        return json;
    }

    public String getString() {
        return string;
    }
}
