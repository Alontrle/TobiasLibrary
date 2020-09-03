package com.tobiassteely.tobiasapi.api.manager;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class CoreManager {

    private static CoreManager instance;

    public static CoreManager getInstance() {
        return instance;
    }

    private Map<String, ManagerParent> managerMap;
    private List<ManagerParent> managers;

    public CoreManager() {
        instance = this;
        this.managerMap = new ConcurrentHashMap<>();
        this.managers = new Vector<>();
    }

    public List<ManagerParent> getManagers() {
        return managers;
    }

    void registerManager(ManagerParent manager) throws ManagerException {
        if(managerMap.containsKey(manager.getIdentifier())) {
            throw new ManagerException(manager);
        } else {
            managerMap.put(manager.getIdentifier(), manager);
            managers.add(manager);
        }
    }

    public ManagerParent getManager(String identifier) {
        return managerMap.get(identifier);
    }

}
