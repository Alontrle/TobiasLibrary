package com.tobiassteely.tobiasapi.api;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.log.Log;
import com.tobiassteely.tobiasapi.command.CommandManager;
import com.tobiassteely.tobiasapi.config.ConfigManager;
import com.tobiassteely.tobiasapi.database.MongoManager;

public class TobiasObject {

    public TobiasAPI getTobiasAPI() {
        return TobiasAPI.getInstance();
    }

    public Log getLog() {
        return TobiasAPI.getInstance().getLog();
    }

    public CommandManager getCommandManager() {
        return TobiasAPI.getInstance().getCommandManager();
    }

    public ConfigManager getConfigManager() {
        return TobiasAPI.getInstance().getConfigManager();
    }

    public MongoManager getMongoManager() {
        return TobiasAPI.getInstance().getMongoManager();
    }

}
