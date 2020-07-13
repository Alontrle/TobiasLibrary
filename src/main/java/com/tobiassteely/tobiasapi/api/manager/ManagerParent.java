package com.tobiassteely.tobiasapi.api.manager;

import com.tobiassteely.tobiasapi.api.TobiasObject;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class ManagerParent extends TobiasObject {

    private Vector<ManagerObject> list = null;
    private ConcurrentHashMap<String, ManagerCache> cacheList = null;

    protected ManagerParent(boolean loadNow) {
        if(loadNow) {
            reload();
        } else {
            list = new Vector<>();
            cacheList = new ConcurrentHashMap<>();
            cacheList.put("key", new ManagerCache());
        }
    }

    public Vector<ManagerObject> getList() {
        return list;
    }

    protected void addCache(String key, ManagerCache cache) {
        cacheList.put(key, cache);
    }

    protected void addCache(String key) {
        cacheList.put(key, new ManagerCache());
    }

    protected ManagerCache getCache(String key) {
        return cacheList.get(key);
    }

    protected void addObject(ManagerObject object) {
        list.add(object);
        getCache("key").putObject(object.getKey(), object);
    }

    protected Object getObject(String key) {
        return getObject("key", key);
    }

    protected Object getObject(String cache, String key) {
        return getCache(cache).getObject(key);
    }

    protected void reload() {
        list = new Vector<>();
        cacheList = new ConcurrentHashMap<>();
        cacheList.put("key", new ManagerCache());
    }

    public void removeObject(String key) {
        list.remove(cacheList.get("key").getObject(key));
        cacheList.get("key").removeObject(key);
    }

}
