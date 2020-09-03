package com.tobiassteely.tobiasapi.api.manager;

import com.tobiassteely.tobiasapi.api.TobiasObject;
import com.tobiassteely.tobiasapi.config.Config;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class ManagerParent<T extends ManagerObjectInterface> extends TobiasObject {

    private String identifier;
    private Vector<T> list;
    private ConcurrentHashMap<String, ManagerCache<T>> cacheList = null;
    private Config data;
    private ManagerEventExecutor<T> eventExecutor;

    protected ManagerParent(String identifier, boolean loadNow, String settings, ManagerEventExecutor<T> executor) {
        this.identifier = identifier;
        this.eventExecutor = executor;
        load(settings, loadNow);
    }

    protected ManagerParent(String identifier) {
        this(identifier, true, "", null);
    }

    protected ManagerParent(String identifier, ManagerEventExecutor<T> executor) {
        this(identifier, true, "", executor);
    }

    protected ManagerParent(String identifier, boolean loadNow, ManagerEventExecutor<T> executor) {
        this(identifier, loadNow, "", executor);
    }

    protected ManagerParent(String identifier, boolean loadNow) {
        this(identifier, loadNow, "", null);
    }

    public void setEventExecutor(ManagerEventExecutor<T> executor) {
        this.eventExecutor = executor;
    }

    public void setData(Config data) {
        this.data = data;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<T> getList() {
        return list;
    }

    public ManagerCache<T> getCache(String key) {
        if(!cacheList.containsKey(key)) {
            cacheList.put(key, new ManagerCache<T>());
        }
        return cacheList.get(key);
    }

    public void addObject(T object) {

        if(eventExecutor != null) {
            eventExecutor.onObjectAdd(this, object);
        }

        list.add(object);
        getCache("key").putObject(object.getKey(), object);
    }

    public T getObject(String key) {
        T object = getCache("key").getObject(key);

        if(object != null) {
            return object;
        }

        for (ManagerCache<T> cache : new ConcurrentHashMap<>(cacheList).values()) {
            object = cache.getObject(key);
            if(object != null) {
                return object;
            }
        }
        return null;
    }

    protected void reload() {
        list = new Vector<>();

        if(data != null && eventExecutor != null) {
            for (Object obj : data.getJSON().keySet()) {
                addObject(eventExecutor.loadObject(data.getJSON(obj.toString())));
            }
        }

        if(eventExecutor != null) {
            eventExecutor.onReload(this);
        }
    }

    public void removeObject(String key) {

        if(eventExecutor != null) {
            eventExecutor.onObjectRemove(this, key, getObject(key));
        }

        list.remove(cacheList.get("key").getObject(key));
        cacheList.get("key").removeObject(key);
    }

    public Config getData() {
        return data;
    }

    public boolean contains(String id) {
        return getObject(id) != null;
    }

    public void save() {
        JSONObject tempData = new JSONObject();
        if(eventExecutor != null) {
            if(data != null) {
                data.reset();
            }

            for (T obj : new Vector<>(list)) {
                JSONObject json = eventExecutor.saveObject(obj);
                if (json != null) {
                    if(data != null) {
                        data.set(obj.getKey(), json);
                    } else {
                        tempData.put(obj.getKey(), json);
                    }
                }
            }

            if(data != null) {
                data.save();
            }
        }

        if(eventExecutor != null) {
            if(data != null) {
                eventExecutor.onSave(this, data.getJSON());
            } else {
                eventExecutor.onSave(this, tempData);
            }
        }
    }

    public void load(String settings, boolean loadNow) {
        cacheList = new ConcurrentHashMap<>();
        cacheList.put("key", new ManagerCache<>());
        list = new Vector<>();
        if(settings.length() > 0) {
            this.data = getConfigManager().getConfig(settings);
        }

        try {
            CoreManager.getInstance().registerManager(this);
        } catch (ManagerException e) {
            e.printStackTrace();
        }

        if(eventExecutor != null) {
            eventExecutor.onLoad(this);
        }

        if(loadNow) {
            reload();
        }

    }

    public void unload() {
        list = null;
        cacheList = null;
        data = null;

        if(eventExecutor != null) {
            eventExecutor.onUnload(this);
        }
    }

}
