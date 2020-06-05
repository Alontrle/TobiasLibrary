package com.tobiassteely.tobiasapi.command.type.basic;

import com.tobiassteely.tobiasapi.command.BaseCommandResponse;

public interface BasicCommandExecutor {

    void run(String name, String[] args);

}
