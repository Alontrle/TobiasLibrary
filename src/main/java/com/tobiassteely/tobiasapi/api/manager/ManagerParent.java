package com.tobiassteely.tobiasapi.api.manager;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.TobiasObject;
import com.tobiassteely.tobiasapi.config.Config;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class ManagerParent<T extends ManagerObject> extends TobiasObject {

    private String identifier; // Official standard is 3 char identifier then a name (EX: API.Command)
    private Vector<T> list = null;
    private ConcurrentHashMap<String, ManagerCache> cacheList = null;
    private Config settings = null;

    protected ManagerParent(String identifier, boolean loadNow) {

        this.identifier = identifier;

        try {
            Managers.getInstance().registerManager(this);
        } catch (ManagerException e) {
            e.printStackTrace();
        }

        list = new Vector<>();
        cacheList = new ConcurrentHashMap<>();
        cacheList.put("key", new ManagerCache());
        if(loadNow) {
            reload();
        }
    }

    protected ManagerParent(String identifier, boolean loadNow, String settings) {
        this(identifier, false);
        this.settings = getConfigManager().getConfig(settings);
        if(loadNow) {
            reload();
        }
    }

    public String getIdentifier() {
        return identifier;
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
        settings.reset();
        for(T obj : new Vector<>(list)) {
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
