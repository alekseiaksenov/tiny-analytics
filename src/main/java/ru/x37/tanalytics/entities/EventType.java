package ru.x37.tanalytics.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_types")
public class EventType {
    @Id
    String name;

    public EventType() {
    }

    public EventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EventType{" +
                "name='" + name + '\'' +
                '}';
    }
}
