package com.tobiassteely.tobiasapi.api.manager;

import java.util.HashMap;

public class ManagerCache<T extends ManagerObjectInterface> {

    private HashMap<String, T> cache;

    public ManagerCache() {
        cache = new HashMap<>();
    }

    public void putObject(String key, T object) {
        cache.put(key, object);
    }

    public boolean isCached(String key) {
        return cache.containsKey(key);
    }

    public T getObject(String key) {
        return cache.get(key);
    }

    public void removeObject(String key) {
        cache.remove(key);
    }

    public HashMap<String, T> getCache() {
        return cache;
    }

}
