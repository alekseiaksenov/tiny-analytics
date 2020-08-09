package ru.x37.tanalytics.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.x37.tanalytics.entities.Event;
import ru.x37.tanalytics.entities.EventType;

public interface EventRepository extends PagingAndSortingRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    static Specification<Event> havingEventType(EventType eventType) {
        return (config, cq, cb) -> cb.equal(config.get("eventType"), eventType);
    }

    static Specification<Event> after(long timestamp) {
        return (config, cq, cb) -> cb.greaterThanOrEqualTo(config.get("timestamp"), timestamp);
    }

    static Specification<Event> before(long timestamp) {
        return (config, cq, cb) -> cb.lessThanOrEqualTo(config.get("timestamp"), timestamp);
    }

    default Page<Event> findByEventType(EventType type, Pageable pageable) {
        return findAll(havingEventType(type), pageable);
    }

    default Page<Event> findByTimestamps(long from, long to, Pageable pageable) {
        return findAll(after(from).and(before(to)), pageable);
    }

    default Page<Event> findByEventTypeAndTimestamps(EventType type, long from, long to, Pageable pageable) {
        return findAll(havingEventType(type).and(after(from).and(before(to))), pageable);
    }
}
