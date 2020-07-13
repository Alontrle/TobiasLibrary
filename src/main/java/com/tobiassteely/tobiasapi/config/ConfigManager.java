package com.tobiassteely.tobiasapi.config;

import com.tobiassteely.tobiasapi.api.manager.ManagerParent;

public class ConfigManager extends ManagerParent {

    private String baseDirectory;

    public ConfigManager(String baseDirectory) {
        super(false);
        this.baseDirectory = baseDirectory;
    }

    public Config getConfig(String configName) {

        if(getObject(configName) == null) {
            Config config;
            if(baseDirectory == null) {
                config = new Config(configName);
            } else {
                config = new Config(baseDirectory + System.getProperty("file.separator") + configName);
            }
            addObject(config);
            return config;
        }

        return (Config) getObject(configName);
    }

    public void removeConfig(String configName) {
        removeObject(configName);
    }

    @Override
    public void reload() {
        super.reload();
    }

}
