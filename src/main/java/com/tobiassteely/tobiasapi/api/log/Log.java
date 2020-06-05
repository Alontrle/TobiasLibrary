package com.tobiassteely.tobiasapi.api.log;

public class Log {

    private Logger logger;

    public Log() {
        logger = new BaseLogger();
    }

    public void sendMessage(int level, String description) {
        logger.log(level, description);
    }

    public void sendMessage(int level, String title, String description) {
        logger.log(level, title, description);
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }
}
