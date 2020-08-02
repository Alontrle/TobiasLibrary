package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.command.ConsoleUser;
import com.tobiassteely.tobiasapi.command.permission.user.PermissionUser;

public class CommandData<T> {

    private String command;
    private String[] args;
    private T data;
    private String inputType;
    private PermissionUser user;

    public static final String command_line_input = "commandline";

    public CommandData(String command, String[] args, T data, String inputType, PermissionUser user) {
        this.command = command;
        this.args = args;
        this.data = data;
        this.inputType = inputType;
        this.user = user;
    }

    public T get() {
        return data;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public PermissionUser getUser() {
        return user;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
}
