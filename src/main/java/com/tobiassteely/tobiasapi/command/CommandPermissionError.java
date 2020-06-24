package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.command.data.CommandData;
import com.tobiassteely.tobiasapi.command.response.CommandResponse;

public interface CommandPermissionError {

    CommandResponse getResponse(String[] args, CommandData data);

}
