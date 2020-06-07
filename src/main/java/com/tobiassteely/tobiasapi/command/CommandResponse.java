package com.tobiassteely.tobiasapi.command;

public interface CommandResponse {

    String command_line_input = "commandline";

    void send(String title, String text, String inputType);

    void send(String text, String inputType);

}
