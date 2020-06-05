package com.tobiassteely.tobiasapi.command.type.template;

import com.tobiassteely.tobiasapi.api.manager.ManagerObject;
import com.tobiassteely.tobiasapi.api.manager.ManagerParent;
import com.tobiassteely.tobiasapi.command.type.template.type.TextCommand;
import com.tobiassteely.tobiasapi.config.Config;
import org.json.simple.JSONObject;

public class TemplateManager extends ManagerParent {

    public TemplateManager() {
        super(false);
    }

    public void registerTemplate(TemplateCommand templateCommand) {
        addObject(templateCommand);
        getCommandManager().registerCommand(templateCommand);
    }

    public void reload() {
        super.reload();

        Config config = getConfigManager().getConfig("commands.json");
        JSONObject json = config.toJson();
        for(Object key : json.keySet()) {
            String type = config.getMap((String)key).get("type");
            TemplateCommand command = new TextCommand(config.getMap((String)key));

            registerTemplate(command);
        }
    }

    public void save() {
        Config config = getConfigManager().getConfig("commands.json");
        for(ManagerObject object : getList()) {
            TemplateCommand templateCommand = (TemplateCommand) object;
            config.toJson().put(templateCommand.getKey(), templateCommand.getJSON());
        }
        config.save();
    }

}
