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
        return eventRepository.findByEventType(type.orElse(null), pageable);
    }

    @Override
    public Page<Event> getEvents(Long from, Long to, Pageable pageable) {
        return eventRepository.findBy(null, from, to, pageable);
    }

    @Override
    @Transactional
    public Page<Event> getEvents(String eventType, Long from, Long to, Pageable pageable) {
        Optional<EventType> type = eventTypeRepository.findById(eventType);
        return eventRepository.findBy(type.orElse(null), from, to, pageable);
    }

    @Override
    @Transactional
    public void persistEvent(Event event) {
        Optional<EventType> type = eventTypeRepository.findById(event.getEventType().getName());
        eventRepository.save(event.setEventType(type.orElseThrow(()
                -> new IllegalArgumentException("No such event type"))));
    }
}
