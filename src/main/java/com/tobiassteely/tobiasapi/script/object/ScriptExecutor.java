package com.tobiassteely.tobiasapi.script.object;

public interface ScriptExecutor<Response, Parameters> {

    Response run(ScriptObject<Parameters> parameters);

}
