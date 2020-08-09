package ru.x37.tanalytics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.x37.tanalytics.entities.Event;
import ru.x37.tanalytics.entities.EventType;
import ru.x37.tanalytics.repositories.EventRepository;
import ru.x37.tanalytics.repositories.EventTypeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class JpaEventService implements EventService {
    @Autowired
    EventTypeRepository eventTypeRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<EventType> getEventTypes() {
        return eventTypeRepository.findAll();
    }

    @Override
    public Page<Event> getEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Page<Event> getEvents(String eventType, Pageable pageable) {
        Optional<EventType> type = eventTypeRepository.findById(eventType);
        return eventRepository.findByEventType(type.get(), pageable);
    }

    @Override
    public Page<Event> getEvents(long from, long to, Pageable pageable) {
        return eventRepository.findByTimestamps(from, to, pageable);
    }

    @Transactional
    public Page<Event> getEvents(String eventType, long from, long to, Pageable pageable) {
        Optional<EventType> type = eventTypeRepository.findById(eventType);
        return eventRepository.findByEventTypeAndTimestamps(type.get(), from, to, pageable);
    }

    @Override
    @Transactional
    public Page<Event> getEvents(String eventType, Long from, Long to, Pageable pageable) {
        if (eventType == null && from == null && to == null) return getEvents(pageable);
        if (eventType != null && from == null && to == null) return getEvents(eventType, pageable);
        return getEvents(eventType, from == null ? 0L : from, to == null ? System.currentTimeMillis() : to, pageable);
    }

    @Override
    @Transactional
    public void persistEvent(Event event) {
        Optional<EventType> eventType = eventTypeRepository.findById(event.getEventType().getName());
        eventRepository.save(event.setEventType(eventType.get()));
    }
}
