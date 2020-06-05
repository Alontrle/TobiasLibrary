package com.tobiassteely.tobiasapi.api.dependency;

import org.json.simple.JSONObject;

public interface DependencyObject {

    int data_type_string = 0;
    int data_type_array = 1;
    int data_type_integer = 2;
    int data_type_long = 3;
    int data_type_double = 4;

    JSONObject getJson();

    String getString();

}
