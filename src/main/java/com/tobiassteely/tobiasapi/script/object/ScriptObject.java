package com.tobiassteely.tobiasapi.script.object;

public class ScriptObject<T> {

    private T object;

    public ScriptObject(T object) {
        this.object = object;
    }

    public void set(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }

    @Override
    public String toString() {
        return object.toString();
    }

}
