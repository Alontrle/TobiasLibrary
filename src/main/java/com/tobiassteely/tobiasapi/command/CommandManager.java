package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.api.manager.ManagerCache;
import com.tobiassteely.tobiasapi.api.manager.ManagerParent;
import com.tobiassteely.tobiasapi.command.type.basic.BasicCommand;
import com.tobiassteely.tobiasapi.command.type.basic.BasicCommandExecutor;
import com.tobiassteely.tobiasapi.command.type.legacy.LegacyCommand;
import com.tobiassteely.tobiasapi.command.type.template.TemplateManager;

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

    private ConcurrentHashMap<String, Vector<BasicCommand>> basicCommandsByModule;
    private ConcurrentHashMap<String, BasicCommand> basicCommandsByName;
    private TemplateManager templateManager;
    private CommandResponse response;

    public CommandManager() {
        super(false);
        addCache("activators", new ManagerCache());
        addCache("modules", new ManagerCache());
        this.executor = Executors.newFixedThreadPool(10);
        this.commandWorker = new CommandWorker();
        this.basicCommandsByModule = new ConcurrentHashMap<>();
        this.basicCommandsByName = new ConcurrentHashMap<>();
        this.templateManager = new TemplateManager();
        this.response = new BaseCommandResponse();

        registerPingCommand();
        registerEndCommand();
    }

    private void registerPingCommand() {
        BasicCommandExecutor executor = (name, args) -> {
            getCommandManager().getResponse().send("Pong!");
        };
        BasicCommand basicCommand = new BasicCommand("Core", "Ping", "Returns with a pong!", 0, executor);

        registerBasicCommand(basicCommand);
    }

    private void registerEndCommand() {
        BasicCommandExecutor executor = (name, args) -> {
            getLog().sendMessage(0, "Goodbye.");
            System.exit(0);
        };
        BasicCommand basicCommand = new BasicCommand("Core", "End", "Shutdown the bot!", 10, executor);

        registerBasicCommand(basicCommand);
    }

    public CommandResponse getResponse() {
        return response;
    }

    private void registereeEndCommand() {

        BasicCommandExecutor executor = (name, args) -> {
            String title = args[0];
            StringBuilder message = new StringBuilder();
            for(int i = 1; i < args.length; i++) {
                message.append(args[i]);

                if(i != args.length - 1) {
                    message.append(args[i]).append(" ");
                }
            }

            getCommandManager().getResponse().send(title, message.toString());
        };
        BasicCommand basicCommand = new BasicCommand("Core", "Embed", "Embed a message!", 5, executor);

        registerBasicCommand(basicCommand);
    }

    public void createSimpleCommand(String command, String description, int staffLevel, String response) {
        BasicCommandExecutor executor = (name, args) -> {
            getCommandManager().getResponse().send(response);
        };
        BasicCommand basicCommand = new BasicCommand("Core", command, description, staffLevel, executor);

        registerBasicCommand(basicCommand);
    }

    public void registerCommand(LegacyCommand command) {
        addObject(command);
        if(!getCache("modules").isCached(command.getModule()))
            getCache("modules").putObject(command.getModule(), new ArrayList<>());

        ArrayList<Object> commands = (ArrayList<Object>)getCache("modules").getObject(command.getModule());
        commands.add(command);

        for(String activator : command.getActivators())
            getCache("activators").putObject(activator.toLowerCase(), command);
    }

    public void registerBasicCommand(BasicCommand command) {
        if(!basicCommandsByModule.containsKey(command.getModule())) {
            basicCommandsByModule.put(command.getModule(), new Vector<>());
        }
        basicCommandsByModule.get(command.getModule()).add(command);

        if(!basicCommandsByName.containsKey(command.getName().toLowerCase())) {
            basicCommandsByName.put(command.getName().toLowerCase(), command);
        } else {
            getLog().sendMessage(2, "Command (" + command.getName() + ") already exists!");
        }

    }

    public boolean runCommand(String input) {
        String commandInput = input.split(" ")[0].toLowerCase();
        String[] args = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);

        if(runLegacyCommand(commandInput, args)) {
            return true;
        } else if(runBasicCommand(commandInput, args)){
            return true;
        }

        getLog().sendMessage(2, "Unknown command, type \"?\" for a list of available commands.");
        return false;
    }

    public boolean runGameCommand(String input) {
        String commandInput = input.split(" ")[0].toLowerCase();
        String[] args = Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);

        if(runLegacyCommand(commandInput, args)) {
            return true;
        } else if(runBasicCommand(commandInput, args)){
            return true;
        }

        runLegacyCommand("help", new String[] {});
        return true;
    }

    public boolean runLegacyCommand(String commandInput, String[] args) {
        if(getCache("activators").isCached(commandInput)) {
            LegacyCommand command = (LegacyCommand)getCache("activators").getObject(commandInput);
            Callable<Object> callableTask = () -> {
                if(!command.run(args)) {
                    getLog().sendMessage(2, "Command registered, but not running properly.");
                }
                return null;
            };
            executor.submit(callableTask);
            return true;
        } else {
            return false;
        }
    }

    public boolean runBasicCommand(String commandInput, String[] args) {
        if(basicCommandsByName.containsKey(commandInput.toLowerCase())) {
            BasicCommand basicCommand = basicCommandsByName.get(commandInput.toLowerCase());

            Callable<Object> callableTask = () -> {
                basicCommand.run(commandInput, args);
                return null;
            };
            executor.submit(callableTask);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<LegacyCommand> getCommandsByModule(String module) {
        if(getCache("modules").isCached(module))
            return (ArrayList<LegacyCommand>)getCache("modules").getObject(module);
        else return null;
    }

    public CommandWorker getCommandWorker() {
        return commandWorker;
    }

    public ConcurrentHashMap<String, BasicCommand> getBasicCommandsByName() {
        return basicCommandsByName;
    }

    public ConcurrentHashMap<String, Vector<BasicCommand>> getBasicCommandsByModule() {
        return basicCommandsByModule;
    }

    public TemplateManager getTemplateManager() {
        return templateManager;
    }
}
