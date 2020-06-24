package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.command.permission.group.PermissionGroup;
import com.tobiassteely.tobiasapi.command.permission.user.PermissionUser;
import org.json.simple.JSONObject;

import java.util.List;

public class ConsoleUser implements PermissionUser {

    @Override
    public List<PermissionGroup> getGroups() {
        return null;
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }

    @Override
    public PermissionGroup getPrimaryGroup() {
        return null;
    }

    @Override
    public String getUserIdentifier() {
        return "Console";
    }

}
