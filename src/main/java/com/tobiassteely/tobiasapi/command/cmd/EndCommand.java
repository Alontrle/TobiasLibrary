package com.tobiassteely.tobiasapi.command.cmd;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.manager.CoreManager;
import com.tobiassteely.tobiasapi.api.manager.ManagerParent;
import com.tobiassteely.tobiasapi.command.Command;
import com.tobiassteely.tobiasapi.command.CommandExecutor;
import com.tobiassteely.tobiasapi.command.response.CommandResponse;

import java.util.Collections;

public class EndCommand {

    private CommandExecutor executor;

    public EndCommand() {
        this.executor = getCode();
    }

    public CommandExecutor getCode() {
        return (data) -> {
            for(ManagerParent parent : CoreManager.getInstance().getManagers()) {
                parent.unload();
            }

            System.exit(0);
            return null;
        };
    }

    public Command build() {
        return new Command("Core", "End", new String[] {"end", "shutdown"}, "end", "Shutdown the bot", "core.end", Collections.singletonList(executor));
    }

}
