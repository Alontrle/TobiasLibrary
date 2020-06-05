package com.tobiassteely.tobiasapi;

import com.tobiassteely.tobiasapi.api.log.Log;
import com.tobiassteely.tobiasapi.command.CommandManager;
import com.tobiassteely.tobiasapi.config.ConfigManager;
import com.tobiassteely.tobiasapi.database.MongoDB;
import com.tobiassteely.tobiasapi.database.MongoManager;

public class TobiasAPI {

    private static TobiasAPI instance;

    public static TobiasAPI getInstance() {
        return instance;
    }

    private ConfigManager configManager;
    private MongoManager mongoManager;
    private CommandManager commandManager;
    private boolean databaseSupport;
    private boolean commandSupport;
    private boolean webServerSupport;
    private Log log;

    public TobiasAPI(String configDirectory, boolean commandSupport, boolean databaseSupport, boolean webServerSupport, String databaseID) {
        instance = this;

        log = new Log();

        // Load database
        this.databaseSupport = databaseSupport;
        if(databaseSupport) {
            if(databaseID != null) {
                this.mongoManager = new MongoManager(new MongoDB(databaseID));
            } else {
                log.sendMessage(2, "The database server has not started, you are missing the ID.");
            }
        }

        // Load config manager
        this.configManager = new ConfigManager(configDirectory);

        // Load command system
        this.commandSupport = commandSupport;
        if(commandSupport) {
            this.commandManager = new CommandManager();
        }

        // Load web server
        this.webServerSupport = webServerSupport;
        if(webServerSupport) {
            // setup web server system
        }
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public MongoManager getMongoManager() {
        if(!databaseSupport) {
            log.sendMessage(2, "The database is not configured this WILL error!");
        }
        return mongoManager;
    }

    public Log getLog() {
        return log;
    }
}
