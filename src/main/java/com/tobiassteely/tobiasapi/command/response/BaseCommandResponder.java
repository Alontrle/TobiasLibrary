package com.tobiassteely.tobiasapi.command.response;

import com.tobiassteely.tobiasapi.api.TobiasObject;

public class BaseCommandResponder extends TobiasObject implements CommandResponder {

    public void send(String title, String text, String inputType, Object... data) {
        getLog().sendMessage(0, title, text);
    }

    public void send(String text, String inputType, Object... data) {
        getLog().sendMessage(0, text);
    }

    @Override
    public void send(CommandResponse response) {
        if(response.getTitle() != null) {
            getLog().sendMessage(0, response.getTitle(), response.getDescription());
        } else {
            getLog().sendMessage(0, response.getDescription());
        }
    }
}
