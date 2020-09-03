package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.api.manager.ManagerEventExecutor;
import com.tobiassteely.tobiasapi.api.manager.ManagerParent;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class CommandEventHandler implements ManagerEventExecutor<Command> {

    @Override
    public void onObjectAdd(ManagerParent<Command> manager, Command command) {
        for(String activator : command.getActivators())
            manager.getCache("activators").putObject(activator.toLowerCase(), command);
    }

    @Override
    public void onObjectRemove(ManagerParent<Command> manager, String key, Command object) {

    }

    @Override
    public void onReload(ManagerParent<Command> manager) {

    }

    @Override
    public void onLoad(ManagerParent<Command> manager) {

    }

    @Override
    public void onUnload(ManagerParent<Command> manager) {

    }

    @Override
    public void onSave(ManagerParent<Command> manager, JSONObject json) {

    }

    @Override
    public JSONObject saveObject(Command object) {
        return null;
    }

    @Override
    public Command loadObject(JSONObject json) {
        return null;
    }

}
