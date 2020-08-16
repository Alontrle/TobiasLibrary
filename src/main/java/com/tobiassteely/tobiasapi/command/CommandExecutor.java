package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.command.response.CommandResponse;

public interface CommandExecutor {

    CommandResponse run(CommandData data) throws Exception;

}
