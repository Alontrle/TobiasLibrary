package com.tobiassteely.tobiasapi.api.event;

import com.tobiassteely.tobiasapi.api.TobiasObject;

import java.util.Vector;

public class EventManager extends TobiasObject {

    private Vector<EventHandler> eventHandlers;

    public EventManager() {
        this.eventHandlers = new Vector<>();
    }

    public void registerEventHandler(EventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public void runEvent(Event event) {
        for(EventHandler eventHandler : eventHandlers) {
            if(!eventHandler.runEvent(event)) {
                getLog().sendMessage(2, "Failed to run an event handler in the event");
            }
        }
    }

}
