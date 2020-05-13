package com.tobiassteely.tobiasapi;

import com.tobiassteely.tobiasapi.api.Log;
import com.tobiassteely.tobiasapi.api.config.ConfigManager;
import com.tobiassteely.tobiasapi.api.database.Mongo;
import com.tobiassteely.tobiasapi.api.database.MongoManager;

public class TobiasAPI {

    private static TobiasAPI instance;

    public static TobiasAPI getInstance() {
        return instance;
    }

    private ConfigManager configManager;
    private Mongo mongo;
    private MongoManager mongoManager;
    private boolean databaseSupport;
    private boolean webserverSupport;
    private boolean commandSupport;

    public TobiasAPI(String databaseID, String configDirectory, boolean webserverSupport, boolean commandSupport) {
        this(configDirectory, webserverSupport, commandSupport);
        this.databaseSupport = true;
        this.mongo = new Mongo(databaseID);
        this.mongoManager = new MongoManager();
    }

    public TobiasAPI(String configDirectory, boolean webserverSupport, boolean commandSupport) {
        instance = this;
        this.configManager = new ConfigManager(configDirectory);
        this.databaseSupport = false;

        this.webserverSupport = webserverSupport;
        if(webserverSupport) {
            // setup web server
            // start web server
        }

        this.commandSupport = commandSupport;
        if(commandSupport) {
            // setup command system
            // start command system
        }

    }

    public TobiasAPI() {
        instance = this;
        this.configManager = new ConfigManager("");
        this.databaseSupport = false;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public Mongo getMongo() {
        if(!databaseSupport) {
            Log.sendMessage(2, "The database is not configured this WILL error!");
        }
        return mongo;
    }

    public MongoManager getMongoManager() {
        if(!databaseSupport) {
            Log.sendMessage(2, "The database is not configured this WILL error!");
        }
        return mongoManager;
    }
}
