package com.lowes.lowesForGeeks.service;

import com.lowes.lowesForGeeks.model.Event;
import com.lowes.lowesForGeeks.model.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Optional<Event> findById(Integer id, Member user);

    List<Event> findAll(Member user);

    List<Event> viewTrendingEvents(Member user);

    List<Event> viewPopularEvents(Member user);

    ResponseEntity postpone(String newStartDate, String newEndDate, Event event, Member user);

    List<Event> viewUpcomingEvents(Member user);

    Event unwatch(Event event);

    Event watch(Event event);

    Event like(Event event);

    Event unlike(Event event);

    List<Event> findByName(String name, Member user);

    ResponseEntity create(Event event, Member creator);

    ResponseEntity delete(Event event, Member deleter);

    ResponseEntity participate(Event event, Member user);

    ResponseEntity unParticipate(Event event, Member user);
}
