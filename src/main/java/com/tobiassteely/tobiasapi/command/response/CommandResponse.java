package com.tobiassteely.tobiasapi.command.response;

import com.tobiassteely.tobiasapi.api.TobiasObject;
import com.tobiassteely.tobiasapi.command.data.CommandData;

public class CommandResponse extends TobiasObject {

    private String title;
    private String description;
    private CommandData data;

    public CommandResponse(CommandData data) {
        this.data = data;
    }

    public CommandResponse setData(CommandData data) {
        this.data = data;
        return this;
    }

    public CommandData getData() {
        return this.data;
    }

    public CommandResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public CommandResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandResponse setInputType(String inputType) {
        this.data.setInputType(inputType);
        return this;
    }

    public String getInputType() {
        return data.getInputType();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
