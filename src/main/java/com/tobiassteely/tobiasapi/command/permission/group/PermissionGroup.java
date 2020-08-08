package com.tobiassteely.tobiasapi.command.permission.group;

import org.json.simple.JSONObject;

import java.util.List;

public interface PermissionGroup {

    List<String> getPermissions();

    boolean hasPermission(String permission);

    String getIdentifier();

}
