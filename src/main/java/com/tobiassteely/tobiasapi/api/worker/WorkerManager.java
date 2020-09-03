package com.tobiassteely.tobiasapi.api.worker;

import com.tobiassteely.tobiasapi.api.manager.ManagerEventExecutor;
import com.tobiassteely.tobiasapi.api.manager.ManagerParent;

public class WorkerManager extends ManagerParent<Worker> {

    private static WorkerManager instance;

    public static WorkerManager getInstance() {
        return instance;
    }

    public WorkerManager() {
        super("API.Worker", true, "", null);
        instance = this;
    }

}
