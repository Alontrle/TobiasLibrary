package com.tobiassteely.tobiasapi.script.object;

public class ScriptNull extends ScriptObject {

    private static ScriptNull scriptNull = new ScriptNull();

    public static ScriptNull getNull() {
        return scriptNull;
    }

    private ScriptNull() {
        super(null);
    }

    @Override
    public Object get() {
        return null;
    }

}
