package com.tobiassteely.tobiasapi.api.event;

public class EventHandler {

    private EventManager eventManager;

    public EventHandler(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public boolean runEvent(Event event) {
        return false; // OVERRIDE
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
