package com.tobiassteely.tobiasapi.database.worker.request;

import com.mongodb.client.MongoCollection;
import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.worker.Worker;
import com.tobiassteely.tobiasapi.database.MongoDocument;
import org.bson.Document;

import java.util.List;
import java.util.Vector;

public class RequestManager extends Worker {

    private MongoCollection<Document> requests;
    private List<RequestEventHandler> events;
    private List<String> recentIDs;

    public RequestManager(MongoCollection<Document> requests) {
        super("API.MongoRequestM", 50);
        this.events = new Vector<>();
        this.recentIDs = new Vector<>();
        this.requests = requests;
    }

    public void registerHandler(RequestEventHandler eventHandler) {
        events.add(eventHandler);
    }

    public Boolean runWorker(long start) {
        for (Document doc : requests.find(new Document("destination", TobiasAPI.getInstance().getMongoManager().getMongoDB().getId()).append("loaded", false))) {

            if(!recentIDs.contains(doc.getString("id"))) {
                recentIDs.add(doc.getString("id"));

                TobiasAPI.getInstance().getMongoManager().getRequestWorker().removeDocument(doc.getString("id"));
                new Thread(() -> {
                    for (RequestEventHandler eventHandler : events) {
                        eventHandler.RequestEventHandler(new MongoDocument(new Document(doc))); // PASSES DOCUMENT
                    }
                }).start();
            }

        }
        return true;
    }

    public void loadManager(String id) {
        for (Document doc : requests.find(new Document("destination", id))) {
            TobiasAPI.getInstance().getMongoManager().getRequestWorker().removeDocument(doc.getString("id"));

            for (RequestEventHandler eventHandler : events) {
                eventHandler.RequestEventHandler(new MongoDocument(doc)); // PASSES DOCUMENT
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
}
