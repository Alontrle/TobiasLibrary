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
        this.mongoDB = mongoDB;
        this.requestManager = new RequestManager(mongoDB.getDatabase().getCollection("requests"));
        this.responseManager = new ResponseManager(mongoDB.getDatabase().getCollection("responses"));
        this.requestWorker = new RequestWorker(mongoDB.getDatabase().getCollection("requests"));
        this.responseWorker = new ResponseWorker(mongoDB.getDatabase().getCollection("responses"));
    }

    public void start() {
        requestManager.start();
        responseManager.start();

        requestManager.loadManager(mongoDB.getId());
        responseManager.loadManager(mongoDB.getId());

        requestWorker.start();
        responseWorker.start();
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
