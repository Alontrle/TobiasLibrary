package com.tobiassteely.tobiasapi.database.worker.request;

import com.tobiassteely.tobiasapi.database.MongoDocument;

public interface RequestEventHandler {

    void RequestEventHandler(MongoDocument document);

}
