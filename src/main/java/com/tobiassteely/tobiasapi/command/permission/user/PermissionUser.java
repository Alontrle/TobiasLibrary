package com.tobiassteely.tobiasapi.command.permission.user;

import com.tobiassteely.tobiasapi.command.permission.group.PermissionGroup;
import org.json.simple.JSONObject;

import java.util.List;

public interface PermissionUser {

    List<PermissionGroup> getGroups();

    boolean hasPermission(String permission);

    PermissionGroup getPrimaryGroup();

    String getUserIdentifier();

}
