package com.tobiassteely.tobiasapi.api.event;

import com.tobiassteely.tobiasapi.api.event.EventManager;

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
