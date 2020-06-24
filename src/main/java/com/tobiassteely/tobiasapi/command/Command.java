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
    private String[] activators;

    public Command(String module, String name, String[] activators, String usage, String description, List<CommandExecutor> executors) {
        super(name);
        this.module = module;
        this.name = name;
        this.description = description;
        this.executors = executors;
        this.activators = activators;
        this.usage = usage;
    }

    public ArrayList<CommandResponse> run(String[] args, CommandData data) {
        ArrayList<CommandResponse> responses = new ArrayList<>();
        for(CommandExecutor executor : executors) {
            CommandResponse response = executor.run(name, args, data);
            if(response != null) {
                responses.add(response);
            }
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
}
