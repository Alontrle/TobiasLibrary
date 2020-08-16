package com.tobiassteely.tobiasapi.api;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.log.Log;
import com.tobiassteely.tobiasapi.api.manager.Managers;
import com.tobiassteely.tobiasapi.command.Command;
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

    public Managers getManager() {
        return Managers.getInstance();
    }

    public double convertToHours(long time) {
        return time / 1000.0 / 60.0 / 60.0;
    }

    public String convertToTime(double time) {
        return (int)time + "h " + (int)((time - (int)time) * 60) + "m";
    }

    public long convertToMs(int hours) {
        return hours * 1000 * 60 * 60;
    }

    private long getTime(String str) {
        long time = 0;

        for(String section : str.toLowerCase().split(",")) {
            if(section.contains("s")) {
                time += Long.parseLong(section.replace("s", "")) * 1000;
            } else if(section.contains("m")) {
                time += Long.parseLong(section.replace("m", "")) * 1000 * 60;
            } else if(section.contains("h")) {
                time += Long.parseLong(section.replace("h", "")) * 1000 * 60 * 60;
            } else if(section.contains("d")) {
                time += Long.parseLong(section.replace("d", "")) * 1000 * 60 * 50 * 24;
            } else if(section.contains("w")) {
                time += Long.parseLong(section.replace("w", "")) * 1000 * 60 * 50 * 24 * 7;
            }
        }
        return time;
    }


}
