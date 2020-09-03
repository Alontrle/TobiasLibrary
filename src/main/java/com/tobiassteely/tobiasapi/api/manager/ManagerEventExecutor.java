package com.tobiassteely.tobiasapi.api.manager;

import org.json.simple.JSONObject;

public interface ManagerEventExecutor<T extends ManagerObjectInterface> {

    public T loadObject(JSONObject json);

    public JSONObject saveObject(T object);

    public void onObjectAdd(ManagerParent<T> manager, T object);

    public void onObjectRemove(ManagerParent<T> manager, String key, T object);

    public void onReload(ManagerParent<T> manager);

    public void onLoad(ManagerParent<T> manager);

    public void onUnload(ManagerParent<T> manager);

    public void onSave(ManagerParent<T> manager, JSONObject json);

}
