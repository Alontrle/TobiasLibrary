package com.tobiassteely.tobiasapi.command.type.template;

import com.tobiassteely.tobiasapi.command.BaseCommandResponse;
import com.tobiassteely.tobiasapi.command.type.legacy.LegacyCommand;
import org.json.simple.JSONObject;

public class TemplateCommand extends LegacyCommand {

    public TemplateCommand(String name, String[] activators, String description, String usage, int staffLevel) {
        super(name, activators, "Core", description, usage, staffLevel);
    }

    @Override
    public boolean run(String[] args) {
        runTemplate(args);
        return true;
    }

    public void runTemplate(String[] args) {
        // OVERRIDE
    }

    public JSONObject getJSON() {
        return null; // OVERRIDE
    }

}
