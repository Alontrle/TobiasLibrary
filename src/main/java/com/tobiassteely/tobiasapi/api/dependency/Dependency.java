package com.tobiassteely.tobiasapi.api.dependency;

import org.json.simple.JSONObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Dependency {

    protected JSONObject json;
    private Map<String, Class<? extends DependencyObject>> dependencyObjectMap;
    private Map<String, DependencyExecutor> dependencyExecutorMap;

    public Dependency()  {
        this.json = new JSONObject();
        this.dependencyObjectMap = new ConcurrentHashMap<>();
        this.dependencyExecutorMap = new ConcurrentHashMap<>();
    }

    public void registerDependencyObject(String identifier, Class<? extends DependencyObject> dependency) {
        dependencyObjectMap.put(identifier, dependency);
    }

    public void registerDependencyExecutor(String identifier, DependencyExecutor dependency) {
        dependencyExecutorMap.put(identifier, dependency);
    }

    public JSONObject getJson() {
        return json;
    }
}
