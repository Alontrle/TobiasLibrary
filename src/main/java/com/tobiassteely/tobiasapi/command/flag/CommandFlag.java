package com.tobiassteely.tobiasapi.command.flag;

import com.tobiassteely.tobiasapi.api.log.Log;
import com.tobiassteely.tobiasapi.api.manager.ManagerObject;

public class CommandFlag extends ManagerObject {

    private String[] identifier;

    public CommandFlag(String key, String... identifier) {
        super(key);
        this.identifier = identifier;
        if(identifier.length == 0) {
            new Log().sendMessage(2, "Command flag (" + key + ") was created with no identifiers!");
        }
    }

    public String[] getIdentifier() {
        return identifier;
    }

}
