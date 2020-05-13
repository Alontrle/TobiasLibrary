package com.tobiassteely.tobiasapi.api.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.config.Config;
import org.bson.Document;

public class Mongo {

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> responses;
    private MongoCollection<Document> requests;
    private String id;

    public Mongo(String id) {
        //Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        //mongoLogger.setLevel(Level.WARNING);
        Config config = TobiasAPI.getInstance().getConfigManager().getConfig("settings.json");

        String mongoUser = config.getString("mongodb-username");
        String mongoPass = config.getString("mongodb-password");
        String mongoHost = config.getString("mongodb-host");
        String mongoDatabase = config.getString("mongodb-db");
        String mongoAuthDatabase = config.getString("mongodb-authdb");

        ConnectionString connString = new ConnectionString(
                "mongodb://" + mongoUser + ":" + mongoPass + "@" + mongoHost + "/" + mongoAuthDatabase + "?w=majority"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        client = MongoClients.create(settings);

        database = client.getDatabase(mongoDatabase);


        responses = database.getCollection("responses");
        requests = database.getCollection("requests");
    }

    public void clear() {
        responses.drop();
        requests.drop();
    }

    public MongoCollection<Document> getResponses() {
        return responses;
    }

    public MongoCollection<Document> getRequests() {
        return requests;
    }

    public String getId() {
        return id;
    }
}
