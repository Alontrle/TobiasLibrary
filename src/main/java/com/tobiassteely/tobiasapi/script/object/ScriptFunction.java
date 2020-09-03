package com.tobiassteely.tobiasapi.script.object;

public class ScriptFunction<Response, Parameters> {

    private ScriptExecutor<Response, Parameters> executor;
    private String name;

    public ScriptFunction(String name, ScriptExecutor<Response, Parameters> executor) {
        this.executor = executor;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Response run(ScriptObject<Parameters> parameters) {
        return executor.run(parameters);
    }

}
