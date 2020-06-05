package com.tobiassteely.tobiasapi.command.type.basic;

import com.tobiassteely.tobiasapi.api.TobiasObject;
import com.tobiassteely.tobiasapi.command.BaseCommandResponse;

public class BasicCommand extends TobiasObject {

    private String module;
    private String name;
    private String description;
    private int staffLevel;
    private BasicCommandExecutor executor;

    public BasicCommand(String module, String name, String description, int staffLevel, BasicCommandExecutor executor) {
        this.module = module;
        this.name = name;
        this.description = description;
        this.staffLevel = staffLevel;
        this.executor = executor;
    }

    public void run(String commandInput, String[] args) {
        executor.run(name, args);
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
}
