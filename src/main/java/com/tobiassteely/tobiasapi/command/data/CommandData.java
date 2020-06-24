package com.tobiassteely.tobiasapi.command.data;

import com.tobiassteely.tobiasapi.command.ConsoleUser;
import com.tobiassteely.tobiasapi.command.permission.user.PermissionUser;

public class CommandData<T> {

    private T data;
    private String inputType;
    private PermissionUser user;

    public static final String command_line_input = "commandline";

    public CommandData(T data, String inputType, PermissionUser user) {
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
}
