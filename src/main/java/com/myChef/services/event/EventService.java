package com.myChef.services.event;

import com.myChef.JPA.Event;
import com.myChef.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Mocart
 */
@Service
public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    public Event getEventByID(long id){
        return eventRepository.findOne(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findByEventDateAfter(new Date());
    }

    public List<Event> getEventsByCity(long cityID) {
        return eventRepository.findByCity_CityIdAndEventDateAfter(cityID, new Date());
    }

    public Event addEvent(Event event){
        logger.debug("Adding event with id %s", event.getEventId());
        event = eventRepository.save(event);

        return event;
    }

    public Event updateEvent(Event event){
        logger.debug("Updating event with id %s", event.getEventId());
        event = eventRepository.save(event);

        return event;
    }

//    REVIEW FOR CORRECTNESS
//    REVIEW FOR CORRECTNESS
//    REVIEW FOR CORRECTNESS
    public void delEvent(long id) {
        Event event = eventRepository.findOne(id);
        if (event != null) {
            logger.debug("Deleting event with id %s",  event.getEventId());

            eventRepository.delete(id);
        }
    }
}
