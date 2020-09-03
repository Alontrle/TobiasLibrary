package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.manager.ManagerCache;
import com.tobiassteely.tobiasapi.api.manager.ManagerParent;
import com.tobiassteely.tobiasapi.command.cmd.EndCommand;
import com.tobiassteely.tobiasapi.command.cmd.HelpCommand;
import com.tobiassteely.tobiasapi.command.permission.user.PermissionUser;
import com.tobiassteely.tobiasapi.command.response.BaseCommandResponder;
import com.tobiassteely.tobiasapi.command.response.CommandResponder;
import com.tobiassteely.tobiasapi.command.response.CommandResponse;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandManager extends ManagerParent<Command> {

    private ExecutorService executor;
    private CommandWorker commandWorker;
    private CommandResponder responder;
    private String welcome;
    private boolean commandLine;
    private CommandPermissionError permissionError;
    private CommandRunCheck commandRunCheck;
    private Map<String, List<Command>> commandCache;

    public CommandManager(String welcome, boolean commandLine) {
        super("API.Command", false, new CommandEventHandler());
        this.commandCache = new ConcurrentHashMap<>();
        this.welcome = welcome;
        this.commandLine = commandLine;
        this.commandRunCheck = (data) -> true;
        this.permissionError = (args, data) -> new CommandResponse(data).setTitle("Error!").setDescription("You do not have permission for that command.");
    }

    public CommandResponder getResponder() {
        return responder;
    }

    public void setResponder(CommandResponder responder) {
        this.responder = responder;
    }

    public void setCommandRunCheck(CommandRunCheck commandRunCheck) {
        this.commandRunCheck = commandRunCheck;
    }

    @Override
    public void addObject(Command command) {
        super.addObject(command);

        if(!commandCache.containsKey(command.getModule())) {
            commandCache.put(command.getModule(), new Vector<>());
        }
        commandCache.get(command.getModule()).add(command);
    }

    public boolean runRawCommandInput(String input, String inputType, PermissionUser user) {
        String commandInput = input.split(" ")[0].toLowerCase();
        String[] args = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);

        if(runCommand(new CommandData(commandInput, args, null, inputType, user))) {
            return true;
        }

        if(user instanceof ConsoleUser) {
            TobiasAPI.getInstance().getLog().sendMessage(2, "Unknown command, type \"?\" for a list of available commands.");
        }
        return false;
    }

    public boolean runCommand(CommandData data) {
        if(getCache("activators").isCached(data.getCommand().toLowerCase())) {
            Command basicCommand = (Command)getCache("activators").getObject(data.getCommand().toLowerCase());

            if(!basicCommand.isConsoleSupported() && data.getUser() instanceof ConsoleUser) {
                return false;
            }

            if(commandRunCheck.check(data)) {
                Callable<Object> callableTask = () -> {
                    ArrayList<CommandResponse> responses = basicCommand.run(data);

                    for (CommandResponse response : responses) {
                        getResponder().send(response);
                    }
                    return null;
                };
                executor.submit(callableTask);
            }

            return true;
        } else {
            return false;
        }

    }

    public List<Command> getCommandsByModule(String module) {
        return commandCache.get(module);
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
        this.executor = Executors.newFixedThreadPool(10);
        this.commandWorker = new CommandWorker(welcome);
        this.responder = new BaseCommandResponder();

        addObject(new HelpCommand().build());
        addObject(new EndCommand().build());
    }

}
