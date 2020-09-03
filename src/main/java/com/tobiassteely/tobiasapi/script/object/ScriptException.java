package com.tobiassteely.tobiasapi.script.object;

import com.tobiassteely.tobiasapi.TobiasAPI;

public class ScriptException extends Exception {

    private int lineNumber;

    public ScriptException(String reason, int lineNumber, String script) {
        super("[" + lineNumber + "] " + reason);
        this.lineNumber = lineNumber;

//        if(script != null) {
//            TobiasAPI.getInstance().getLog().sendMessage(2, "Error \"" + reason + "\" on line " + lineNumber + " in script " + script);
//        } else {
//            TobiasAPI.getInstance().getLog().sendMessage(2, "Error \"" + reason + "\"");
//        }
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
