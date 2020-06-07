package com.tobiassteely.tobiasapi.command.cmd;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.manager.ManagerObject;
import com.tobiassteely.tobiasapi.command.Command;
import com.tobiassteely.tobiasapi.command.CommandExecutor;

import java.util.ArrayList;
import java.util.HashMap;

public class EndCommand {

    private CommandExecutor executor;

    public EndCommand() {
        this.executor = getCode();
    }

    public CommandExecutor getCode() {
        return (name, rawCommand, args, inputType) -> {
            TobiasAPI.getInstance().getLog().sendMessage(0, "Goodbye.");
            System.exit(0);
        };
    }

    public Command build() {
        return new Command("Core", "End", new String[] {"end", "shutdown"}, "end", "Shutdown the bot", 10, executor);
    }

}
