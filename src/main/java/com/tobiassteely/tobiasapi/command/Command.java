package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.api.manager.ManagerObject;

public class Command extends ManagerObject {

    private String module;
    private String name;
    private String description;
    private int staffLevel;
    private CommandExecutor executor;
    private String usage;
    private String[] activators;

    public Command(String module, String name, String[] activators, String usage, String description, int staffLevel, CommandExecutor executor) {
        super(name);
        this.module = module;
        this.name = name;
        this.description = description;
        this.staffLevel = staffLevel;
        this.executor = executor;
        this.activators = activators;
        this.usage = usage;
    }

    public void run(String rawCommand, String[] args, String inputType) {
        executor.run(name, args, inputType);
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

    public int getStaffLevel() {
        return staffLevel;
    }

    public String[] getActivators() {
        return activators;
    }

    public String getUsage() {
        return usage;
    }

    public CommandExecutor getExecutor() {
        return executor;
    }
}
