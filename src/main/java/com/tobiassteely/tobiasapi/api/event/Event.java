package com.tobiassteely.tobiasapi.api.event;

import com.tobiassteely.tobiasapi.api.event.EventManager;

public class Event {

    private EventManager eventManager;

    public Event(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

}
