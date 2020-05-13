package com.tobiassteely.tobiasapi.api.database;

public class MongoException extends Exception {

    public MongoException() {
    }

    public MongoException(String message) {
        super(message);
    }

    public MongoException(String message, Throwable cause) {
        super(message, cause);
    }

    public MongoException(Throwable cause) {
        super(cause);
    }

}
