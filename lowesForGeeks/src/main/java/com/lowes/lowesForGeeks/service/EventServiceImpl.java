package com.lowes.lowesForGeeks.service;

import com.lowes.lowesForGeeks.model.Event;
import com.lowes.lowesForGeeks.model.EventType;
import com.lowes.lowesForGeeks.model.Member;
import com.lowes.lowesForGeeks.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import javax.xml.bind.DatatypeConverter;
import java.util.*;

@Service("eventService")
public class EventServiceImpl implements EventService {

    private static long day = 86400000;
    private static long day2 = day *2;
    private static long week1 = day * 7;
    private static long week2 = day * 14;
    private static long month1 = day *30;
    private static long month2 = day * 60;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TeamService teamService;

    @Override
    public Optional<Event> findById(Integer id, Member user) {
        System.out.println("Hey");
        Optional<Event> event = eventRepository.findById(id);
        System.out.println(event.get().getNumberOfViews());
        if(event.isPresent()) {
            if(event.get().getNumberOfViews()!=null)
                event.get().setNumberOfViews(event.get().getNumberOfViews() + 1);
            else
                event.get().setNumberOfViews(1);
            eventRepository.save(event.get());
        }
        return  event;
    }

    @Override
    public List<Event> findAll(Member user) {
        Iterable<Event> events = eventRepository.findAll(user.getTeam_id());
        for( Event e : events) {
            if(e.getNumberOfViews()!=null)
                e.setNumberOfViews(e.getNumberOfViews() + 1);
            else
                e.setNumberOfViews(1);
            eventRepository.save(e);
        }
        return getListFromIterator(events);
    }

    @Override
    public List<Event> findByName(String name, Member user) {
        Iterable<Event> event = eventRepository.findByName(user.getId(),name);
        for( Event e : event) {
            if(e.getNumberOfViews()!=null)
                e.setNumberOfViews(e.getNumberOfViews() + 1);
            else
                e.setNumberOfViews(1);

        }
        return getListFromIterator(event);
    }



    @Override
    public List<Event> viewTrendingEvents(Member user) {
        Integer userTeamId = user.getTeam_id();
        long today = Calendar.getInstance().getTimeInMillis();
        if(!user.isOrganizationAdmin())
            return getListFromIterator(eventRepository.findTrendingEvents(userTeamId, EventType.Private, today));
        else
            return getListFromIterator(eventRepository.findTrendingEvents(EventType.Private, today));
    }

    @Override
    public List<Event> viewPopularEvents(Member user) {
        Integer userTeamId = user.getTeam_id();
        long today = Calendar.getInstance().getTimeInMillis();
        if(!user.isOrganizationAdmin())
            return getListFromIterator(eventRepository.findPopularEvents(userTeamId, EventType.Private, today));
        else
            return getListFromIterator(eventRepository.findPopularEvents(EventType.Private, today));
    }


    @Override
    public List<Event> viewUpcomingEvents(Member user) {
        Integer userTeamId = user.getTeam_id();
        long today = Calendar.getInstance().getTimeInMillis();
        if (!user.isOrganizationAdmin())
            return getListFromIterator(eventRepository.findUpcomingEvents(userTeamId, EventType.Private, today));
        else
            return getListFromIterator(eventRepository.findTrendingEvents(EventType.Private, today));
    }

    @Override
    public ResponseEntity postpone(String newStartDate, String newEndDate, Event event, Member user) {
        Calendar cal = DatatypeConverter.parseDateTime(newStartDate);
        long nd = cal.getTimeInMillis();
        long od = DatatypeConverter.parseDateTime(event.getStartDateTime()).getTimeInMillis();
        long today = Calendar.getInstance().getTimeInMillis();
        if(event.getEventType() == EventType.Private && (nd-od) > day2)
        {
            if(event.getCreatedBy().getId() == user.getId())
            {
                event.setStartDateTime(newStartDate);
                event.setEndDateTime(newEndDate);
                return new ResponseEntity(eventRepository.save(event), HttpStatus.OK);
            }
        }
        else if(event.getEventType() == EventType.Team && (nd-od) > week1)
        {
            if((event.getCreatorTeamId() == user.getTeam_id() && user.isTeamAdmin()) || user.isOrganizationAdmin()) {
                event.setStartDateTime(newStartDate);
                event.setEndDateTime(newEndDate);
                return new ResponseEntity(eventRepository.save(event), HttpStatus.OK);
            }
        }

        else if(event.getEventType() == EventType.Org && (nd-od) > new Long(month1))
        {

        }
        return new ResponseEntity("Postponement not possible", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Event like(Event event)
    {
        if(event.getNumberOfLikes()!=null)
            event.setNumberOfLikes(event.getNumberOfLikes()+1);
        else
            event.setNumberOfLikes(1);
        return eventRepository.save(event);
    }
    @Override
    public Event unlike(Event event)
    {
        event.setNumberOfLikes(event.getNumberOfLikes()-1);
        return eventRepository.save(event);
    }
    @Override
    public Event watch(Event event)
    {
        if(event.getNumberOfWatchers()!=null)
            event.setNumberOfWatchers(event.getNumberOfWatchers()+1);
        else
            event.setNumberOfWatchers(1);
        return eventRepository.save(event);
    }
    @Override
    public Event unwatch(Event event)
    {
        event.setNumberOfWatchers(event.getNumberOfWatchers()-1);
        return eventRepository.save(event);
    }

    @Override
    public ResponseEntity participate(Event event, Member user) {
        event.getParticipants().add(user);
        if(event.getNumberOfParticipants()==null)
            event.setNumberOfParticipants(1);
        else
            event.setNumberOfParticipants(event.getNumberOfParticipants()+1);
        return new ResponseEntity(eventRepository.save(event), HttpStatus.OK);
    }

    @Override
    public ResponseEntity unParticipate(Event event, Member user) {
        event.getParticipants().remove(user);
        if(event.getNumberOfParticipants()==null)
            event.setNumberOfParticipants(1);
        else
            event.setNumberOfParticipants(event.getNumberOfParticipants()+1);
        return new ResponseEntity(eventRepository.save(event), HttpStatus.OK);
    }


    @Override
    public ResponseEntity create(Event event, Member creator) {
        String start=event.getStartDateTime();
        Calendar calendar= DatatypeConverter.parseDateTime(start);
        long dateOfEvent = calendar.getTimeInMillis();
        System.out.println(dateOfEvent);
        long today = Calendar.getInstance().getTimeInMillis();
        System.out.println(today);
        long diff=dateOfEvent-today;
        System.out.println(diff);
        event.setCreatedBy(creator);
        event.setCreatorTeamId(creator.getTeam_id());
        event.setCreationTime(today);
        event.setStartDateInMills(dateOfEvent);
        calendar = DatatypeConverter.parseDateTime(event.getEndDateTime());
        event.setEndDateInMills(calendar.getTimeInMillis());
        if(event.getEventType()== EventType.Private && diff>day2)
            return new ResponseEntity(eventRepository.save(event), HttpStatus.OK);
        if(event.getEventType()==EventType.Team && (creator.isTeamAdmin()||creator.isOrganizationAdmin()) && diff>week1)
            return new ResponseEntity(eventRepository.save(event), HttpStatus.OK);
        if(event.getEventType()==EventType.Org && creator.isOrganizationAdmin() && diff>month2)
            return new ResponseEntity(eventRepository.save(event),  HttpStatus.OK);
        throw new ValidationException("Not authorized.");
    }

    @Override
    public ResponseEntity delete(Event event, Member deleter) {
        Member eventCreator = event.getCreatedBy();
        Integer creatorTeamId = eventCreator.getTeam_id();
        String start=event.getStartDateTime();
        Calendar calendar= DatatypeConverter.parseDateTime(start);
        long dateOfEvent = calendar.getTimeInMillis();
        long today = Calendar.getInstance().getTimeInMillis();
        long diff=dateOfEvent-today;
        if(event.getEventType()==EventType.Private || event.getEventType()==EventType.Team)
        {
            if(event.getCreatedBy().getId() == deleter.getId()) {
                eventRepository.delete(event);
                return new ResponseEntity("Delete successful", HttpStatus.OK);
            }
            else
                throw new ValidationException("Not Authorized");
        }
        else if(event.getEventType()==EventType.Org)
        {
            if(deleter.isOrganizationAdmin())
            {
                eventRepository.delete(event);
                return new ResponseEntity("Delete successful", HttpStatus.OK);
            }
            throw new ValidationException("Not authorized");
        }
        throw  new ValidationException("Not authorized");
    }



    private static List<Event>
    getListFromIterator(Iterable<Event> iterator)
    {

        // Create an empty list
        List<Event> list = new ArrayList<>();

        // Add each element of iterator to the List
        iterator.forEach(list::add);

        // Return the List
        return list;
    }

}
