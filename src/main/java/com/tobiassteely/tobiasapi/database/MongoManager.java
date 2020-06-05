package com.tobiassteely.tobiasapi.database;

import com.tobiassteely.tobiasapi.database.worker.request.RequestManager;
import com.tobiassteely.tobiasapi.database.worker.request.RequestWorker;
import com.tobiassteely.tobiasapi.database.worker.response.ResponseManager;
import com.tobiassteely.tobiasapi.database.worker.response.ResponseWorker;

public class MongoManager {

    private RequestWorker requestWorker;
    private ResponseWorker responseWorker;
    private RequestManager requestManager;
    private ResponseManager responseManager;
    private MongoDB mongoDB;

    public MongoManager(MongoDB mongoDB) {
        this.requestWorker = new RequestWorker();
        this.responseWorker = new ResponseWorker();

        this.requestManager = new RequestManager();
        this.responseManager = new ResponseManager();
    }

    public void start() {
        requestWorker.start();
        responseWorker.start();

        requestManager.start();
        responseManager.start();

        requestManager.loadManager();
        responseManager.loadManager();
    }

    public MongoDB getMongoDB() {
        return mongoDB;
    }

    public ResponseManager getResponseManager() {
        return responseManager;
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }

    public RequestWorker getRequestWorker() {
        return requestWorker;
    }

    public ResponseWorker getResponseWorker() {
        return responseWorker;
    }
}
