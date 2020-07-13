package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.manager.ManagerCache;
import com.tobiassteely.tobiasapi.api.manager.ManagerParent;
import com.tobiassteely.tobiasapi.command.cmd.EndCommand;
import com.tobiassteely.tobiasapi.command.cmd.HelpCommand;
import com.tobiassteely.tobiasapi.command.data.CommandData;
import com.tobiassteely.tobiasapi.command.permission.user.PermissionUser;
import com.tobiassteely.tobiasapi.command.response.BaseCommandResponder;
import com.tobiassteely.tobiasapi.command.response.CommandResponder;
import com.tobiassteely.tobiasapi.command.response.CommandResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandManager extends ManagerParent {

    private ExecutorService executor;
    private CommandWorker commandWorker;
    private CommandResponder responder;
    private String welcome;
    private boolean commandLine;
    private CommandPermissionError permissionError;

    public CommandManager(String welcome, boolean commandLine) {
        super(false);
        this.welcome = welcome;
        this.commandLine = commandLine;
        this.permissionError = (args, data) -> new CommandResponse(data).setTitle("Error!").setDescription("You do not have permission for that command.");
    }

    public CommandResponder getResponder() {
        return responder;
    }

    public void setResponder(CommandResponder responder) {
        this.responder = responder;
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

    public boolean runRawCommandInput(String input, CommandData data) {
        String commandInput = input.split(" ")[0].toLowerCase();
        String[] args = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);

        if(runCommand(commandInput, args, data)) {
            return true;
        }

        TobiasAPI.getInstance().getLog().sendMessage(2, "Unknown command, type \"?\" for a list of available commands.");
        return false;
    }

    public boolean runRawCommandInput(String input, String inputType, PermissionUser user) {
        String commandInput = input.split(" ")[0].toLowerCase();
        String[] args = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);

        if(runCommand(commandInput, args, new CommandData(null, inputType, user))) {
            return true;
        }

        TobiasAPI.getInstance().getLog().sendMessage(2, "Unknown command, type \"?\" for a list of available commands.");
        return false;
    }

    public boolean runCommand(String commandInput, String[] args, CommandData data) {
        if(getCache("activators").isCached(commandInput.toLowerCase())) {
            Command basicCommand = (Command)getCache("activators").getObject(commandInput.toLowerCase());

            Callable<Object> callableTask = () -> {
                ArrayList<CommandResponse> responses = basicCommand.run(args, data);

                for(CommandResponse response : responses) {
                    getResponder().send(response);
                }
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

    public Command getCommand(String key) {
        return (Command)getObject(key);
    }

    public CommandPermissionError getPermissionError() {
        return permissionError;
    }

    public void setPermissionError(CommandPermissionError permissionError) {
        this.permissionError = permissionError;
    }

    public void reload() {
        super.reload();
        addCache("activators", new ManagerCache());
        addCache("modules", new ManagerCache());
        this.executor = Executors.newFixedThreadPool(10);
        this.commandWorker = new CommandWorker(welcome);
        this.responder = new BaseCommandResponder();

        registerCommand(new HelpCommand().build());
        registerCommand(new EndCommand().build());
    }

}
