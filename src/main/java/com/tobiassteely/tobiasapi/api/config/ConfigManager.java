package com.tobiassteely.tobiasapi.api.config;

import com.tobiassteely.tobiasapi.api.manager.ManagerParent;

public class ConfigManager extends ManagerParent {

    private String baseDirectory;

    public ConfigManager(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public Config getConfig(String configName) {

        if(getObjectWithKey(configName) == null) {
            Config config = new Config(baseDirectory + System.getProperty("file.separator") + configName);
            addObject(config);
            return config;
        }

        return (Config) getObjectWithKey(configName);
    }

}
