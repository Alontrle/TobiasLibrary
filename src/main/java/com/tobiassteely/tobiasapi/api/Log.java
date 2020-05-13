package com.tobiassteely.tobiasapi.api;

public class Log {

    public static void sendMessage(int status, String msg) {
        if(status == 0)
            System.out.println("[INFO] " + msg);
        else if(status == 1)
            System.out.println("[WARN] " + msg);
        else if(status == 2)
            System.out.println("[ERROR] " + msg);
    }

}
