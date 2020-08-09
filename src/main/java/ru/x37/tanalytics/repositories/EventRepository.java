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
        return (config, cq, cb) -> eventType != null ? cb.equal(config.get("eventType"), eventType) : cb.isNotNull(config.get("eventType"));
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

    default Page<Event> findBy(EventType type, Long from, Long to, Pageable pageable) {
        Specification<Event> resultSpec = havingEventType(type);
        if (from != null) resultSpec = resultSpec.and(after(from));
        if (to != null) resultSpec = resultSpec.and(before(to));
        return findAll(resultSpec, pageable);
    }
}
