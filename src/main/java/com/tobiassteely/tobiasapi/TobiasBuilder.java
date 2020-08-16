package com.tobiassteely.tobiasapi;

import com.tobiassteely.tobiasapi.api.manager.Managers;
import com.tobiassteely.tobiasapi.command.CommandManager;
import com.tobiassteely.tobiasapi.config.ConfigManager;
import com.tobiassteely.tobiasapi.database.MongoDB;
import com.tobiassteely.tobiasapi.database.MongoManager;

public class TobiasBuilder {

    private Managers manager;
    private ConfigManager configManager = null;
    private MongoManager mongoManager = null;
    private CommandManager commandManager = null;

    public TobiasBuilder() {
        this.manager = new Managers();
    }

    public TobiasBuilder loadConfigManager(String basefolder) {
        this.configManager = new ConfigManager(basefolder);
        return this;
    }

    public TobiasBuilder loadMongoManager(String id, String host, String database, String authdb, String username, String password) {
        this.mongoManager = new MongoManager(new MongoDB(id, host, database, authdb, username, password));
        return this;
    }

    public TobiasBuilder loadCommandManager(String welcome, boolean commandLine) {
        this.commandManager = new CommandManager(welcome, commandLine);
        return this;
    }

    public TobiasAPI build(boolean startCommandLine) {
        TobiasAPI api = new TobiasAPI(manager, configManager, mongoManager, commandManager);
        if(api.isConfigEnabled()) {
            configManager.reload();
        }
        if(api.isCommandEnabled()) {
            commandManager.reload();
            if(commandManager.isCommandLine()) {
                if(startCommandLine) {
                    commandManager.getCommandWorker().start();
                }
            }
        }
        return api;
    }

}
