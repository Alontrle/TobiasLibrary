package com.tobiassteely.tobiasapi.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.config.Config;

public class MongoDB {

    private MongoClient client;
    private MongoDatabase database;
    private String id;

    public MongoDB(String id) {
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
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public String getId() {
        return id;
    }
}
