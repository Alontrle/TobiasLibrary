package com.tobiassteely.tobiasapi.api.event;

public class Event {

    private EventManager eventManager;

    public Event(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

}
