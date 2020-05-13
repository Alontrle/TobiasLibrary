package com.tobiassteely.tobiasapi.api.event;

import com.tobiassteely.tobiasapi.api.Log;

import java.util.Vector;

public class EventManager {

    private Vector<com.tobiassteely.tobiasapi.api.event.EventHandler> eventHandlers;

    public EventManager() {
        this.eventHandlers = new Vector<>();
    }

    public void registerEventHandler(com.tobiassteely.tobiasapi.api.event.EventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public void runEvent(com.tobiassteely.tobiasapi.api.event.Event event) {
        for(com.tobiassteely.tobiasapi.api.event.EventHandler eventHandler : eventHandlers) {
            if(!eventHandler.runEvent(event)) {
                Log.sendMessage(2, "Failed to run an event handler in the event");
            }
        }
    }

}
