package ru.x37.tanalytics.entities;

import java.io.Serializable;

public class EventDTO implements Serializable {
    String eventType;
    String description;
    String additional;

    public EventDTO() {
    }

    public EventDTO(String eventType, String description, String additional) {
        this.eventType = eventType;
        this.description = description;
        this.additional = additional;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Event toEvent() {
        return new Event()
                .setEventType(new EventType(eventType))
                .setDescription(description)
                .setAdditional(additional)
                .setTimestamp(System.currentTimeMillis());
    }
}
