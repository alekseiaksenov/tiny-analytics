package ru.x37.tanalytics.entities;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "name")
    EventType eventType;

    @Column
    String description;

    @Column
    String additional;

    @Column
    long timestamp;

    public Event() {
    }

    public long getId() {
        return id;
    }

    public Event setId(long id) {
        this.id = id;
        return this;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Event setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAdditional() {
        return additional;
    }

    public Event setAdditional(String additional) {
        this.additional = additional;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Event setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventType=" + eventType +
                ", description='" + description + '\'' +
                ", additional='" + additional + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String toLogString() {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return String.format("[%s] %s – %s (%s)", eventType.getName(), formatter.format(timestamp), description, additional);
    }
}
