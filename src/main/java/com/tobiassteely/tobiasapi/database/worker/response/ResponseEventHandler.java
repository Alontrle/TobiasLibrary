package com.tobiassteely.tobiasapi.database.worker.response;


import com.tobiassteely.tobiasapi.database.MongoDocument;

public interface ResponseEventHandler {

    void ResponseEventHandler(MongoDocument document);

}
