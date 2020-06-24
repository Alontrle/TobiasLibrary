package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.command.data.CommandData;
import com.tobiassteely.tobiasapi.command.permission.user.PermissionUser;
import com.tobiassteely.tobiasapi.command.response.CommandResponse;

public interface CommandExecutor {

    CommandResponse run(String name, String[] args, CommandData data);

}
