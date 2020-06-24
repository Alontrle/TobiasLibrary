package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.command.data.CommandData;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandWorker extends Thread {

    private boolean isActive = true;
    private String welcome;

    public CommandWorker(String welcome) {
        this.welcome = welcome;
    }

    @Override
    public void run() {

        TobiasAPI.getInstance().getLog().sendMessage(0, "-----------------------------------------");
        TobiasAPI.getInstance().getLog().sendMessage(0, welcome);
        TobiasAPI.getInstance().getLog().sendMessage(0, "-----------------------------------------");

        CommandManager commandManager = TobiasAPI.getInstance().getCommandManager();
        TobiasAPI.getInstance().getLog().sendMessage(0, "Type \"?\" to view possible commands");
        while(true) {
            if(!isActive)
                break;

            Scanner scanner = new Scanner(System.in);
            try {
                String command = scanner.nextLine();
                if(command != null)
                    commandManager.runRawCommandInput(command, CommandData.command_line_input, new ConsoleUser());
            } catch (NoSuchElementException ignored) {}
        }
        System.exit(0);
    }

    @Override
    public void interrupt() {
        TobiasAPI.getInstance().getLog().sendMessage(0, "Attempting soft shutdown.");
        isActive = false;
        try {
            Thread.sleep(10000);
            TobiasAPI.getInstance().getLog().sendMessage(1, "Frozen for 10 seconds, forcing shutdown.");
            super.interrupt();
            System.exit(0);

        } catch (InterruptedException ignore) {}
    }

}
