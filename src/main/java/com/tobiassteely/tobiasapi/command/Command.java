package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.manager.ManagerObject;
import com.tobiassteely.tobiasapi.command.data.CommandData;
import com.tobiassteely.tobiasapi.command.response.CommandResponse;

import java.util.ArrayList;
import java.util.List;

public class Command extends ManagerObject {

    private String module;
    private String name;
    private String description;
    private List<CommandExecutor> executors;
    private String usage;
    private String permission;
    private String[] activators;
    private boolean consoleSupported;

    public Command(String module, String name, String[] activators, String usage, String description, String permission, List<CommandExecutor> executors) {
        super(name);
        this.module = module;
        this.name = name;
        this.description = description;
        this.executors = executors;
        this.activators = activators;
        this.permission = permission;
        this.usage = usage;
        this.consoleSupported = true;
    }

    public Command(String module, String name, String[] activators, String usage, String description, String permission, List<CommandExecutor> executors, boolean consoleSupported) {
        super(name);
        this.module = module;
        this.name = name;
        this.description = description;
        this.executors = executors;
        this.activators = activators;
        this.permission = permission;
        this.usage = usage;
        this.consoleSupported = consoleSupported;
    }

    public boolean isConsoleSupported() {
        return consoleSupported;
    }

    public ArrayList<CommandResponse> run(String[] args, CommandData data) {
        ArrayList<CommandResponse> responses = new ArrayList<>();
        if(permission == null || data.getUser().hasPermission(permission)) {
            for (CommandExecutor executor : executors) {
                CommandResponse response = executor.run(name, args, data);
                if (response != null) {
                    responses.add(response);
                }
            }
        } else {
            responses.add(getCommandManager().getPermissionError().getResponse(args, data));
        }
        return responses;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getModule() {
        return module;
    }

    public String[] getActivators() {
        return activators;
    }

    public String getUsage() {
        return usage;
    }

    public List<CommandExecutor> getExecutors() {
        return executors;
    }

    public Command setExecutors(List<CommandExecutor> executors) {
        this.executors = executors;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivators(String[] activators) {
        this.activators = activators;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
