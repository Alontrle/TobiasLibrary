package com.tobiassteely.tobiasapi.command.cmd;

import com.tobiassteely.tobiasapi.api.TobiasObject;
import com.tobiassteely.tobiasapi.api.manager.ManagerObject;
import com.tobiassteely.tobiasapi.command.Command;
import com.tobiassteely.tobiasapi.command.CommandExecutor;
import com.tobiassteely.tobiasapi.command.response.CommandResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class HelpCommand extends TobiasObject {

    private CommandExecutor executor;

    public HelpCommand() {
        this.executor = getCode();
    }

    public CommandExecutor getCode() {
        return (name, args, data) -> {
            HashMap<String, ArrayList<String>> commands = new HashMap<>();

            for(ManagerObject object : getCommandManager().getList()) {
                Command legacyCommand = (Command) object;
                if(!commands.containsKey(legacyCommand.getModule())) {
                    commands.put(legacyCommand.getModule(), new ArrayList<>());
                }

                commands.get(legacyCommand.getModule()).add(legacyCommand.getUsage() + " - " + legacyCommand.getDescription());
            }

            StringBuilder description = new StringBuilder();

            for(String module : commands.keySet()) {
                ArrayList<String> lines = commands.get(module);

                description.append("**").append(module).append(" Commands**\n");
                for (String line : lines) {
                    description.append(line).append("\n");
                }
            }

            return new CommandResponse(data).setTitle("**Available Commands**").setDescription(description.toString());
        };
    }

    public Command build() {
        return new Command("Core", "Help", new String[] {"help", "?"}, "help", "Sends this message", null, Collections.singletonList(executor));
    }


}
