package com.lowes.lowesForGeeks.controller;
import com.google.common.collect.Iterables;
import com.lowes.lowesForGeeks.model.Event;
import com.lowes.lowesForGeeks.model.Member;
import com.lowes.lowesForGeeks.repository.MemberRepository;
import com.lowes.lowesForGeeks.service.EventService;
import com.lowes.lowesForGeeks.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    EventService eventService;
    @Autowired
    MemberService memberService;

    @GetMapping("lowesforgeeks/event")
    public ResponseEntity read(@RequestHeader(name = "loggedInMemberId") Integer id)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        List<Event> events =  eventService.findAll(user);
        if(events.size()>0)
            return new ResponseEntity(events, HttpStatus.OK);
        else
            throw new NoSuchElementException("No events to show");
    }

    @GetMapping("lowesforgeeks/event/{eventId}")
    public ResponseEntity read(@RequestHeader(name = "loggedInMemberId") Integer id, @PathVariable Integer eventId)
    {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        Optional<Event> event =  eventService.findById(eventId, user);
        if(event.isPresent())
            return new ResponseEntity(event, HttpStatus.OK);
        else
            throw new NoSuchElementException("No event found by this id");
    }

    @GetMapping("lowesforgeeks/event/search/{name}")
    public ResponseEntity read(@RequestHeader(name = "loggedInMemberId") Integer id, @PathVariable String name)
    {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        List<Event> events =  eventService.findByName(name, user);
        if(events.size()>0)
            return new ResponseEntity(events, HttpStatus.OK);
        else
            throw new NoSuchElementException("No event found by this name");
    }

    @GetMapping("lowesforgeeks/event/trending")
    public ResponseEntity trending(@RequestHeader(name = "loggedInMemberId") Integer id)
    {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        List<Event> events =  eventService.viewTrendingEvents(user);
        if(events.size()>0)
            return new ResponseEntity(events, HttpStatus.OK);
        else
            throw new NoSuchElementException("No event found by this id");
    }
    @GetMapping("lowesforgeeks/event/popular")
    public ResponseEntity popular(@RequestHeader(name = "loggedInMemberId") Integer id)
    {

        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        List<Event> events =  eventService.viewPopularEvents(user);
        if(events.size()>0)
            return new ResponseEntity(events, HttpStatus.OK);
        else
            throw new NoSuchElementException("No event found by this id");
    }
    @GetMapping("lowesforgeeks/event/upcoming")
    public ResponseEntity upcoming(@RequestHeader(name = "loggedInMemberId") Integer id)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        List<Event> events =  eventService.viewUpcomingEvents(user);
        if(events.size()>0)
            return new ResponseEntity(events, HttpStatus.OK);
        else
            throw new NoSuchElementException("No event found by this id");
    }
    @PutMapping("lowesforgeeks/event/watch/{eventId}")
    public ResponseEntity watch(@RequestHeader(name = "loggedInMemberId") Integer id, @PathVariable Integer eventId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        Optional<Event> event = eventService.findById(eventId, user);
        if(event.isPresent())
            return new ResponseEntity(eventService.watch(event.get()), HttpStatus.OK);
        else
            throw new ValidationException("Event not found");
    }

    @PutMapping("lowesforgeeks/event/unwatch/{eventId}")
    public ResponseEntity unwatch(@RequestHeader(name = "loggedInMemberId") Integer id, @PathVariable Integer eventId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        Optional<Event> event = eventService.findById(eventId, user);
        if(event.isPresent())
            return new ResponseEntity(eventService.unwatch(event.get()), HttpStatus.OK);
        else
            throw new ValidationException("Event not found");
    }
    @PutMapping("lowesforgeeks/event/like/{eventId}")
    public ResponseEntity like(@RequestHeader(name = "loggedInMemberId") Integer id, @PathVariable Integer eventId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        Optional<Event> event = eventService.findById(eventId, user);
        if(event.isPresent())
            return new ResponseEntity(eventService.like(event.get()), HttpStatus.OK);
        else
            throw new ValidationException("Event not found");
    }

    @PutMapping("lowesforgeeks/event/unlike/{eventId}")
    public ResponseEntity unlike(@RequestHeader(name = "loggedInMemberId") Integer id, @PathVariable Integer eventId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        Optional<Event> event = eventService.findById(eventId, user);
        if(event.isPresent())
            return new ResponseEntity(eventService.unlike(event.get()), HttpStatus.OK);
        else
            throw new ValidationException("Event not found");
    }

    @PutMapping("lowesforgeeks/event/participate/{eventId}")
    public ResponseEntity participate(@RequestHeader(name = "loggedInMemberId")Integer id, @PathVariable(name = "eventId") Integer eventId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        Optional<Event> event = eventService.findById(eventId, user);
        if(event.isPresent())
        {
            return eventService.participate(event.get(), user);
        }

        else
            throw new NoSuchElementException("Event not found");
    }

    @PutMapping("lowesforgeeks/event/unparticipate/{eventId}")
    public ResponseEntity unparticipate(@RequestHeader(name = "loggedInMemberId")Integer id, @PathVariable(name = "eventId") Integer eventId)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user =memberService.findById(id).get();
        Optional<Event> event = eventService.findById(eventId, user);
        if(event.isPresent())
            return eventService.unParticipate(event.get(), user);
        else
            throw new NoSuchElementException("Event not found");
    }

    @PostMapping("lowesforgeeks/event")
    public ResponseEntity create( @RequestHeader(name = "loggedInMemberId") Integer id , @RequestBody Event event)
    {
        if(!memberService.findById(id).isPresent())
            throw new NoSuchElementException("Invalid login");

        Member user = memberService.findById(id).get();
        return eventService.create(event, user);
    }
}
