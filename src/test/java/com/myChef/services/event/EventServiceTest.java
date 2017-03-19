package com.myChef.services.event;

import com.myChef.JPA.Event;
import com.myChef.repository.EventRepository;
import com.myChef.services.geo.GEOService;
import com.myChef.services.user.UserService;
import com.myChef.utils.EntityIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Mocart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventServiceTest {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private GEOService geoService;


    @Test
    public void getEventByID() throws Exception {
        Event event = eventService.getEventByID(5l);
        assertTrue("christening".equals(event.getDescription()));

    }

    @Test
    public void getAllEvents() throws Exception {
        int count = eventService.getAllEvents().size();
        assert (count == 5);
    }

    @Test
    public void getEventsByCity() throws Exception {
        int count = eventService.getEventsByCity(15).size();
        assert (count == 5);
    }

    @Test
    public void addEvent() throws Exception {
        long id = EntityIdGenerator.random();

        while (eventRepository.exists(id)) {
            id = EntityIdGenerator.random();
        }

        Event event = new Event();
        event.setEventId(id);
        event.setDescription("test event");

        String date = "21/03/2020";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Date d = sdf.parse(date);

        event.setEventDate(d);
        event.setCity(geoService.getCityById(21));
        event.setUser(userService.getUserById(2l).getUserDetails());

        eventService.addEvent(event);

        Event ev = eventService.getEventByID(id);
        assertNotNull("Event not found", ev);
        assertTrue("Events are not equal", "test event".equals(ev.getDescription()) &&
                2 == ev.getUser().getUserId());

        eventService.delEvent(id);
        ev = eventService.getEventByID(id);
        assertNull("Can not delete user", ev);

    }
}