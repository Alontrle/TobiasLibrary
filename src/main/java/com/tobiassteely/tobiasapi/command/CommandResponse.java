package com.tobiassteely.tobiasapi.command;

public interface CommandResponse {

    void send(String title, String text);

    void send(String text);

}
