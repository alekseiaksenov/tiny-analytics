package ru.x37.tanalytics.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.x37.tanalytics.entities.Event;
import ru.x37.tanalytics.entities.EventType;

import java.util.List;

public interface EventService {
    List<EventType> getEventTypes();
    Page<Event> getEvents(Pageable pageable);
    Page<Event> getEvents(String eventType, Pageable pageable);
    Page<Event> getEvents(long from, long to, Pageable pageable);
    Page<Event> getEvents(String eventType, Long from, Long to, Pageable pageable);

    void persistEvent(Event event);
}
