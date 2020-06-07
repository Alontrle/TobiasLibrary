package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.api.TobiasObject;

public class BaseCommandResponse extends TobiasObject implements CommandResponse {

    public void send(String title, String text, String inputType) {
        getLog().sendMessage(0, title, text);
    }

    public void send(String text, String inputType) {
        getLog().sendMessage(0, text);
    }

}