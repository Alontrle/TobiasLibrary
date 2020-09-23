package com.tobiassteely.tobiasapi.script;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class ScriptParser {

    private Path file;
    private Script script;

    public ScriptParser(Path file) {
        this.file = file;
        this.script = new Script(file.toString());
    }

    public Script parseScript() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file.toString()));
        String line = reader.readLine();
        while (line != null) {
            parseLine(line);
            // read next line
            line = reader.readLine();
        }
        reader.close();
        return script;
    }

    public void parseLine(String line) {
        if (line.contains("(") && line.contains(")")) {

        } else if (line.contains("=")) {

        } else {

        }
    }

}
