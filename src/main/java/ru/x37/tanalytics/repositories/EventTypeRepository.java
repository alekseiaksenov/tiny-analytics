package ru.x37.tanalytics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.x37.tanalytics.entities.EventType;

public interface EventTypeRepository extends JpaRepository<EventType, String> {
}
