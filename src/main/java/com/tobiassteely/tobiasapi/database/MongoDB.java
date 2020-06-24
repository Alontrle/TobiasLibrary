package com.tobiassteely.tobiasapi.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    private MongoClient client;
    private MongoDatabase mongoDatabase;
    private String id;

    public MongoDB(String id, String host, String database, String authdb, String username, String password) {
        //Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        //mongoLogger.setLevel(Level.WARNING);
        this.id = id;


        ConnectionString connString = new ConnectionString(
                "mongodb://" + username + ":" + password + "@" + host + "/" + authdb + "?w=majority"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        client = MongoClients.create(settings);

        mongoDatabase = client.getDatabase(database);
    }

    public MongoDatabase getDatabase() {
        return mongoDatabase;
    }

    public String getId() {
        return id;
    }
}
