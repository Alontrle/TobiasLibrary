package com.tobiassteely.tobiasapi.api.log;

public class BaseLogger implements Logger {

    @Override
    public void log(int level, String title, String description) {
        if(level == level_info)
            System.out.println("[INFO] " + title + " : " + description);
        else if(level == level_warn)
            System.out.println("[WARN] " + title + " : " + description);
        else if(level == level_error)
            System.out.println("[ERROR] " + title + " : " + description);
        else if(level == level_critical)
            System.out.println("[CRITICAL] " + title + " : " + description);
    }

    @Override
    public void log(int level, String description) {
        if(level == level_info)
            System.out.println("[INFO] " + description);
        else if(level == level_warn)
            System.out.println("[WARN] " + description);
        else if(level == level_error)
            System.out.println("[ERROR] " + description);
        else if(level == level_critical)
            System.out.println("[CRITICAL] " + description);
    }

}
