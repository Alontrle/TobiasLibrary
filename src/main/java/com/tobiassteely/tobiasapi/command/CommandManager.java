package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.manager.ManagerCache;
import com.tobiassteely.tobiasapi.api.manager.ManagerParent;
import com.tobiassteely.tobiasapi.command.cmd.EndCommand;
import com.tobiassteely.tobiasapi.command.cmd.HelpCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandManager extends ManagerParent {

    private ExecutorService executor;
    private CommandWorker commandWorker;

    private CommandResponse response;
    private String welcome;
    private boolean commandLine;

    public CommandManager(String welcome, boolean commandLine) {
        super(false);
        this.welcome = welcome;
        this.commandLine = commandLine;
    }

    public CommandResponse getResponse() {
        return response;
    }

    public void setResponse(CommandResponse response) {
        this.response = response;
    }

    public void registerCommand(Command command) {
        addObject(command);
        if(!getCache("modules").isCached(command.getModule()))
            getCache("modules").putObject(command.getModule(), new ArrayList<>());

        ArrayList<Object> commands = (ArrayList<Object>)getCache("modules").getObject(command.getModule());
        commands.add(command);

        for(String activator : command.getActivators())
            getCache("activators").putObject(activator.toLowerCase(), command);
    }

    public boolean runCommand(String input, String inputType) {
        String commandInput = input.split(" ")[0].toLowerCase();
        String[] args = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);

        if(runCommand(commandInput, args, inputType)) {
            return true;
        }

        TobiasAPI.getInstance().getLog().sendMessage(2, "Unknown command, type \"?\" for a list of available commands.");
        return false;
    }

    public boolean runCommand(String commandInput, String[] args, String inputType) {
        if(getCache("activators").isCached(commandInput.toLowerCase())) {
            Command basicCommand = (Command)getCache("activators").getObject(commandInput.toLowerCase());

            Callable<Object> callableTask = () -> {
                basicCommand.run(commandInput, args, inputType);
                return null;
            };
            executor.submit(callableTask);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Command> getCommandsByModule(String module) {
        if(getCache("modules").isCached(module))
            return (ArrayList<Command>)getCache("modules").getObject(module);
        else return null;
    }

    public CommandWorker getCommandWorker() {
        return commandWorker;
    }

    public boolean isCommandLine() {
        return commandLine;
    }

    public void reload() {
        super.reload();
        addCache("activators", new ManagerCache());
        addCache("modules", new ManagerCache());
        this.executor = Executors.newFixedThreadPool(10);
        this.commandWorker = new CommandWorker(welcome);
        this.response = new BaseCommandResponse();

        registerCommand(new HelpCommand().build());
        registerCommand(new EndCommand().build());
    }
}
