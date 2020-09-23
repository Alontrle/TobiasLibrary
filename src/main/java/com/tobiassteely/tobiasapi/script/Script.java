package com.tobiassteely.tobiasapi.script;

import com.tobiassteely.tobiasapi.api.manager.ManagerObject;
import com.tobiassteely.tobiasapi.script.object.ScriptException;
import com.tobiassteely.tobiasapi.script.object.ScriptFunction;
import com.tobiassteely.tobiasapi.script.object.ScriptNull;
import com.tobiassteely.tobiasapi.script.object.ScriptObject;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.lang.reflect.Parameter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Script extends ManagerObject {

    public static void main(String[] args) {
        new Script("script test");
    }

    private Map<String, ScriptFunction> functions;
    private Map<String, ScriptObject> variables;

    public Script(String name) {
        super(name);

        this.functions = new ConcurrentHashMap<>();
        this.variables = new ConcurrentHashMap<>();

        // load default functions
        ScriptFunction print = new ScriptFunction<ScriptNull, ScriptObject>("print", (data) -> {
            System.out.println(data.toString());
            return ScriptNull.getNull();
        });
        functions.put(print.getName(), print);

        try {
            runFunction("print", 0, new ScriptObject<>("I want pie"));
        } catch (ScriptException e) {
            System.out.println(e.getMessage());
        }

        String data; // load from file

        // parse data

    }

    public <Response> Response runFunction(String name, int lineNumber, ScriptObject parameters) throws ScriptException {
        ScriptFunction func = functions.get(name);
        if(func != null) {
            return (Response)func.run(parameters);
        } else {
            throw new ScriptException("Unknown Function: " + name, lineNumber, getKey());
        }
    }

}
