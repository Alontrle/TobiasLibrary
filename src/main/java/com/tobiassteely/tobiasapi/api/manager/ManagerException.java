package com.tobiassteely.tobiasapi.api.manager;

public class ManagerException extends Exception {

    public ManagerParent manager;

    public ManagerException(ManagerParent manager) {
        super("Manager identifier is already in use!");
        this.manager = manager;
    }

    public ManagerParent getManager() {
        return manager;
    }
}
