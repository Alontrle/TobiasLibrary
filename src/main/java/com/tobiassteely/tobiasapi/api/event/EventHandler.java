package com.tobiassteely.tobiasapi.api.event;

import com.tobiassteely.tobiasapi.api.event.EventManager;

public class EventHandler {

    private EventManager eventManager;

    public EventHandler(com.tobiassteely.tobiasapi.api.event.EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public boolean runEvent(com.tobiassteely.tobiasapi.api.event.Event event) {
        return false; // OVERRIDE
    }

    public com.tobiassteely.tobiasapi.api.event.EventManager getEventManager() {
        return eventManager;
    }
}
