package com.tobiassteely.tobiasapi.command.cmd;

import com.tobiassteely.tobiasapi.TobiasAPI;
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
        return (name, args, data) -> {
            TobiasAPI.getInstance().getLog().sendMessage(0, "Goodbye.");
            System.exit(0);
            return new CommandResponse(data).setDescription("Goodbye.");
        };
    }

    public Command build() {
        return new Command("Core", "End", new String[] {"end", "shutdown"}, "end", "Shutdown the bot", "core.end", Collections.singletonList(executor));
    }

}
