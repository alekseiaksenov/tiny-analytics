package ru.x37.tanalytics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.x37.tanalytics.entities.Event;
import ru.x37.tanalytics.entities.EventDTO;
import ru.x37.tanalytics.entities.EventType;
import ru.x37.tanalytics.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/")
    public List<Event> getEvents(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long before,
            @RequestParam(required = false) Long after,
            Pageable page) {
        return eventService.getEvents(type, before, after, page).toList();
    }

    @GetMapping("/types/")
    public List<EventType> getEventTypes() {
        return eventService.getEventTypes();
    }

    @PostMapping("/")
    public void persistEvent(@RequestBody EventDTO eventDTO) {
        eventService.persistEvent(eventDTO.toEvent());
    }
}
