package com.tobiassteely.tobiasapi.command.cmd;

import com.tobiassteely.tobiasapi.api.TobiasObject;
import com.tobiassteely.tobiasapi.api.manager.ManagerObject;
import com.tobiassteely.tobiasapi.command.Command;
import com.tobiassteely.tobiasapi.command.CommandExecutor;

import java.util.ArrayList;
import java.util.HashMap;

public class HelpCommand extends TobiasObject {

    private CommandExecutor executor;

    public HelpCommand() {
        this.executor = getCode();
    }

    public CommandExecutor getCode() {
        return (name, args, inputType, data) -> {
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

            getCommandManager().getResponse().send("**Available Commands**", description.toString(), inputType);
        };
    }

    public Command build() {
        return new Command("Core", "Help", new String[] {"help", "?"}, "help", "Sends this message", 0, executor);
    }


}
