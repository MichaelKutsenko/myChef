package com.myChef.rest;

import api.GenericReply;
import api.event.AddEventRequest;
import api.event.EventListReply;
import com.myChef.JPA.Event;
import com.myChef.services.event.EventMapper;
import com.myChef.services.event.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mocart
 */
@RestController
public class EventController {
    private static final Logger logger =  LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;
    @Autowired
    private EventMapper eventMapper;

    @RequestMapping(path="/events/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EventListReply getAllUsers(){
        EventListReply reply = new EventListReply();

        for(Event event: eventService.getAllEvents()){
            reply.events.add(eventMapper.fromInternal(event));
        }
        return reply;
    }

    @RequestMapping(path="/events/byid/{eventID}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EventListReply getEventById(@PathVariable long eventID) {
        EventListReply reply = new EventListReply();

        reply.events.add(eventMapper.fromInternal(eventService.getEventByID(eventID)));

        return reply;
    }

    @RequestMapping(path="/events/bycity/{cityID}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EventListReply getEventsByCity(@PathVariable long cityID ){
        EventListReply reply = new EventListReply();

        for(Event event: eventService.getEventsByCity(cityID)){
            reply.events.add(eventMapper.fromInternal(event));
        }
        return reply;
    }

    @RequestMapping(path="/events/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EventListReply addEvent( @RequestBody AddEventRequest req){
        EventListReply rep = new EventListReply();
        try{
            Event event = eventService.addEvent(eventMapper.toInternal(req.event));
            rep.events.add(eventMapper.fromInternal(event));
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding event. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }

    @RequestMapping(path="/events/update",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EventListReply updateEvent( @RequestBody AddEventRequest req){
        EventListReply rep = new EventListReply();
        try{
            Event event = eventService.updateEvent(eventMapper.toInternal(req.event));
            rep.events.add(eventMapper.fromInternal(event));
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error updating event. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }

    @RequestMapping(path="/events/del/{eventID}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delUser(@PathVariable Long eventID ){
        GenericReply rep = new GenericReply();
        try{
            eventService.delEvent(eventID);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error deleting event. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }
}
