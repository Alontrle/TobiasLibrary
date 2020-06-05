package com.tobiassteely.tobiasapi.command;

import com.tobiassteely.tobiasapi.TobiasAPI;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandWorker extends Thread {

    private boolean isActive = true;

    @Override
    public void run() {

        TobiasAPI.getInstance().getLog().sendMessage(0, "-----------------------------------------");
        TobiasAPI.getInstance().getLog().sendMessage(0, "Xilla Discord Core - 2.0.0");
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
                    commandManager.runCommand(command);
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
