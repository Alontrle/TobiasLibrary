package com.tobiassteely.tobiasapi.api.log;

public interface Logger {

    int level_info = 0;
    int level_warn = 1;
    int level_error = 2;
    int level_critical = 3;

    void log(int level, String title, String description);

    void log(int level, String description);

}
