package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.command.BaseCommandResponse;

public interface CommandExecutor {

    String command_line_input = "commandline";

    void run(String name, String[] args, String inputType, Object... data);

}
