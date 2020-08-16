package com.tobiassteely.tobiasapi;

import com.tobiassteely.tobiasapi.api.log.Log;
import com.tobiassteely.tobiasapi.api.manager.Managers;
import com.tobiassteely.tobiasapi.command.Command;
import com.tobiassteely.tobiasapi.command.CommandManager;
import com.tobiassteely.tobiasapi.config.ConfigManager;
import com.tobiassteely.tobiasapi.database.MongoManager;

public class TobiasAPI {

    private static TobiasAPI instance;

    public static TobiasAPI getInstance() {
        return instance;
    }

    private ConfigManager configManager;
    private MongoManager mongoManager;
    private CommandManager commandManager;
    private Managers manager;
    private Log log;

    public TobiasAPI(Managers manager, ConfigManager configManager, MongoManager mongoManager, CommandManager commandManager) {
        instance = this;

        this.manager = manager;

        log = new Log();

        // Load database
        this.configManager = configManager;
        this.mongoManager = mongoManager;
        this.commandManager = commandManager;
    }

    public ConfigManager getConfigManager() {
        if(configManager == null) {
            log.sendMessage(2, "The config system is not configured this WILL error!");
        }
        return configManager;
    }

    public CommandManager getCommandManager() {
        if(commandManager == null) {
            log.sendMessage(2, "The command system is not configured this WILL error!");
        }
        return commandManager;
    }

    public MongoManager getMongoManager() {
        if(mongoManager == null) {
            log.sendMessage(2, "The database is not configured this WILL error!");
        }
        return mongoManager;
    }

    public void setMongoManager(MongoManager mongoManager) {
        this.mongoManager = mongoManager;
    }

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public boolean isConfigEnabled() {
        return configManager != null;
    }

    public boolean isCommandEnabled() {
        return commandManager != null;
    }

    public boolean isMongoEnabled() {
        return mongoManager != null;
    }

    public Log getLog() {
        return log;
    }
}
