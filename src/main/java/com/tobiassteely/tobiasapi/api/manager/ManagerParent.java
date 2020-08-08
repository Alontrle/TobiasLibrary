package com.tobiassteely.tobiasapi.api.manager;

import com.tobiassteely.tobiasapi.api.TobiasObject;
import com.tobiassteely.tobiasapi.config.Config;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class ManagerParent<T extends ManagerObject> extends TobiasObject {

    private Vector<T> list = null;
    private ConcurrentHashMap<String, ManagerCache> cacheList = null;
    private Config settings = null;

    protected ManagerParent(boolean loadNow) {
        list = new Vector<>();
        cacheList = new ConcurrentHashMap<>();
        cacheList.put("key", new ManagerCache());
        if(loadNow) {
            reload();
        }
    }

    protected ManagerParent(boolean loadNow, String settings) {
        this(false);
        this.settings = getConfigManager().getConfig(settings);
        if(loadNow) {
            reload();
        }
    }

    public List<T> getList() {
        return list;
    }

    protected void addCache(String key, ManagerCache cache) {
        cacheList.put(key, cache);
    }

    protected void addCache(String key) {
        cacheList.put(key, new ManagerCache<T>());
    }

    public ManagerCache getCache(String key) {
        return cacheList.get(key);
    }

    public void addObject(T object) {
        list.add(object);
        getCache("key").putObject(object.getKey(), object);
    }

    public T getObject(String key) {
        return getObject("key", key);
    }

    public T getObject(String cache, String key) {
        return (T) getCache(cache).getObject(key);
    }

    protected void reload() {
        list = new Vector<>();

        if(settings != null) {
            for (Object obj : settings.getJSON().keySet()) {
                if (!loadObject(settings.getJSON(obj.toString()))) {
                    getLog().sendMessage(2, "Failed to load object " + obj.toString());
                }
            }
        }
    }

    public void removeObject(String key) {
        list.remove(cacheList.get("key").getObject(key));
        cacheList.get("key").removeObject(key);
    }

    public Config getSettings() {
        return settings;
    }

    public void save() {
        for(T obj : list) {
            JSONObject json = saveObject(obj);
            if(json != null) {
                settings.set(obj.getKey(), json);
            }
        }
        settings.save();
    }

    public JSONObject saveObject(T object) {
        return null;
    }

    public boolean loadObject(JSONObject json) {
        return false;
    }

}
