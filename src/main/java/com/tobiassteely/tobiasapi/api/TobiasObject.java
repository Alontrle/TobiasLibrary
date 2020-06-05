package com.tobiassteely.tobiasapi.api;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.log.Log;
import com.tobiassteely.tobiasapi.command.CommandManager;
import com.tobiassteely.tobiasapi.config.ConfigManager;
import com.tobiassteely.tobiasapi.database.MongoManager;

public class TobiasObject {

    private TobiasAPI tobiasAPI;
    private ConfigManager configManager;
    private MongoManager mongoManager;
    private CommandManager commandManager;
    private Log log;

    public TobiasObject() {
        this.tobiasAPI = TobiasAPI.getInstance();
        this.configManager = tobiasAPI.getConfigManager();
        this.mongoManager = tobiasAPI.getMongoManager();
    }

    public TobiasAPI getTobiasAPI() {
        return tobiasAPI;
    }

    public Log getLog() {
        return log;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MongoManager getMongoManager() {
        return mongoManager;
    }
}
