package com.tobiassteely.tobiasapi.api.event;

import com.tobiassteely.tobiasapi.api.event.EventManager;

public class Event {

    private EventManager eventManager;

    public Event(com.tobiassteely.tobiasapi.api.event.EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public com.tobiassteely.tobiasapi.api.event.EventManager getEventManager() {
        return eventManager;
    }

}
