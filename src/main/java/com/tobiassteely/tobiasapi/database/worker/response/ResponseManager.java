package com.tobiassteely.tobiasapi.database.worker.response;

import com.mongodb.client.MongoCollection;
import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.worker.Worker;
import com.tobiassteely.tobiasapi.database.MongoDocument;
import org.bson.Document;

import java.util.List;
import java.util.Vector;

public class ResponseManager extends Worker {

    private MongoCollection<Document> responses;
    private List<ResponseEventHandler> events;
    private List<String> recentIDs;

    public ResponseManager(MongoCollection<Document> responses) {
        super(50);
        this.events = new Vector<>();
        this.recentIDs = new Vector<>();
        this.responses = responses;
    }

    public void registerHandler(ResponseEventHandler eventHandler) {
        events.add(eventHandler);
    }

    public Boolean runWorker(long start) {
        for (Document doc : responses.find(new Document("destination", TobiasAPI.getInstance().getMongoManager().getMongoDB().getId()).append("loaded", false))) {
            new Thread(() -> {
                if(!recentIDs.contains(doc.getString("id"))) {
                    for (ResponseEventHandler eventHandler : events) {
                        eventHandler.ResponseEventHandler(new MongoDocument(new Document(doc))); // PASSES DOCUMENT
                    }
                }
            }).start();
        }
        for (Document doc : responses.find(new Document("destination", "Public").append("loaded", false))) {
            new Thread(() -> {
                if(!recentIDs.contains(doc.getString("id"))) {
                    for (ResponseEventHandler eventHandler : events) {
                        eventHandler.ResponseEventHandler(new MongoDocument(new Document(doc))); // PASSES DOCUMENT
                    }
                }
            }).start();
        }
        return true;
    }

    public void loadManager(String id) {
        for (Document doc : responses.find(new Document("destination", id))) {
            for (ResponseEventHandler eventHandler : events) {
                eventHandler.ResponseEventHandler(new MongoDocument(doc)); // PASSES DOCUMENT
            }
        }
    }

    public void removeRecentID(String id) {
        this.recentIDs.remove(id);
    }


    public void addRecentID(String id) {
        this.recentIDs.add(id);
    }

    public boolean isRecent(String id) {
        return recentIDs.contains(id);
    }

    public List<String> getRecentIDs() {
        return recentIDs;
    }
}
