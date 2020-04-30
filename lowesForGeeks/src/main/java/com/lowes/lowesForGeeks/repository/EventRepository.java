package com.lowes.lowesForGeeks.repository;

import com.lowes.lowesForGeeks.model.Event;
import com.lowes.lowesForGeeks.model.EventType;
import com.lowes.lowesForGeeks.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Integer> {

    @Query("select e from Event e where e.creatorTeamId = ?1 and e.name = ?2")
    Iterable<Event> findByName(Integer id,String name);

    @Query("select e from Event e where e.creatorTeamId = ?1")
    Iterable<Event> findAll(Integer id);

    @Query("select e from Event e where e.creatorTeamId = ?1 and e.eventType != ?2 and e.isRecurring = false and (?3 - e.creationTime) > 5184000000 and (e.startDateInMills-?3) > 604800000")
    Iterable<Event> findTrendingEvents(Integer userTeamId, EventType eventType, long today) ;

    @Query("Select e from Event e where e.eventType != ?1 and (?2 - e.creationTime) > 5184000000 and (e.startDateInMills-?2) > 604800000")
    Iterable<Event> findTrendingEvents(EventType aPrivate, long today);

    @Query("select e from Event e where e.creatorTeamId = ?1 and e.eventType != ?2 and e.isRecurring = false and (?3 - e.creationTime) > 2592000000 and (e.startDateInMills-?3) > 172800000")
    Iterable<Event> findPopularEvents(Integer userTeamId, EventType aPrivate, long today);

    @Query("Select e from Event e where e.eventType != ?1 and (?2 - e.creationTime) > 2592000000 and (e.startDateInMills-?2) > 172800000")
    Iterable<Event> findPopularEvents(EventType aPrivate, long today);

    @Query("select e from Event e where e.creatorTeamId = ?1 and e.eventType != ?2 and e.isRecurring = false and (?3 - e.creationTime) > 604800000 and (e.startDateInMills-?3) > 86400000")
    Iterable<Event> findUpcomingEvents(Integer userTeamId, EventType aPrivate, long today);

    @Query("Select e from Event e where e.eventType != ?1 and (?2 - e.creationTime) > 608400000 and (e.startDateInMills-?2) > 86400000")
    Iterable<Event> findUpcomingEvents(EventType aPrivate, long today);


}
