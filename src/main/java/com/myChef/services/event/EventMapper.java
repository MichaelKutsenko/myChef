package com.myChef.services.event;

import api.event.JSONevent;
import api.geo.JSONgeoPoint;
import com.myChef.JPA.Event;
import com.myChef.repository.CityRepository;
import com.myChef.repository.EventRepository;
import com.myChef.repository.UserDetailsRepository;
import com.myChef.services.geo.GEOMapper;
import com.myChef.utils.EntityIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Mocart
 */
@Component
public class EventMapper {
    private static final Logger logger =  LoggerFactory.getLogger(EventMapper.class);
    private Calendar calendar = new GregorianCalendar();

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserDetailsRepository detailsRepository;
    @Autowired
    private CityRepository cityRepository;

    /**
     * Maps internal JPA model to external REST model
     * @param event innternal user model
     * @return external REST user model
     */
    public JSONevent fromInternal(Event event){
        JSONevent json = null;
        if (event != null) {
            json = new JSONevent();

            json.id = event.getEventId();
            json.userFirstName = event.getUser().getFirstName();
            json.userID = event.getUser().getUserId();

            calendar.setTimeInMillis(event.getEventDate().getTime());
            json.day = calendar.get(Calendar.DAY_OF_MONTH);
            json.month = calendar.get(Calendar.MONTH) + 1;
            json.year = calendar.get(Calendar.YEAR);

            json.description = event.getDescription();


            GEOMapper mapper = new GEOMapper();
            JSONgeoPoint city = mapper.fromInternal(event.getCity());

            json.city = city;
        }
        return json;
    }

    /**
     * Maps extrernal REST model to internal Event;
     * If event does not exist in DB then creates new. If event already exists
     * then fetches event from DB and sets all fields from external REST model
     * @param json REST model
     * @return internal Event with all required fields set
     */
    public Event toInternal(JSONevent json) {
        Event event = null;
        //first, check if it exists
        if (json.id != null) {
            event = eventRepository.findOne(json.id);
        }
        //not found, create new
        if (event == null) {
            logger.debug("Creating new event");

            event = new Event();

            Long id = 0L;
            boolean idOK = false;
            while (!idOK) {
                id = EntityIdGenerator.random();
                idOK = !eventRepository.exists(id);
            }
            event.setEventId(id);
        }
        else {
            logger.debug("Updating existing event");
        }

        event.setUser(detailsRepository.findOne(json.userID));
        event.setCity(cityRepository.findOne(json.city.id));

        calendar.set(json.year, json.month - 1, json.day);
        event.setEventDate(new java.util.Date(calendar.getTimeInMillis()));

        event.setDescription(json.description);

        return event;
    }
}
