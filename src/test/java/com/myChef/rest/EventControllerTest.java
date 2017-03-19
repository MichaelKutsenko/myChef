package com.myChef.rest;

import api.event.AddEventRequest;
import api.event.EventListReply;
import api.event.JSONevent;
import api.geo.JSONgeoPoint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mocart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(get("/events/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":2,\"userFirstName\":\"Ivan\",\"userID\":1")));


        MvcResult result = mockMvc.perform(get("/events/all"))
                .andDo(print()).andReturn();

        String reply = result.getResponse().getContentAsString();

        ObjectMapper om = new ObjectMapper();
        EventListReply listReply = om.readValue(reply, EventListReply.class);

        assertTrue(listReply.error_message == null && listReply.events.size() == 5);

    }

    @Test
    public void getEventById() throws Exception {
        mockMvc.perform(get("/events/byid/4"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("after-dinner")));

    }

    @Test
    public void getEventsByCity() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/events/bycity/15"))
                .andExpect(status().isOk())
                .andReturn();

        String reply = result.getResponse().getContentAsString();

        ObjectMapper om = new ObjectMapper();
        EventListReply listReply = om.readValue(reply, EventListReply.class);

        assertTrue(listReply.error_message == null && listReply.events.size() == 5);

    }

    @Test
    public void addEvent() throws Exception {
        AddEventRequest rq = new AddEventRequest();
        rq.event = new JSONevent();
        rq.event.userFirstName = "Test name";
        rq.event.userID = 1;
        rq.event.description = "test";
        rq.event.day = 11;
        rq.event.month = 13;
        rq.event.year = 2020;
        rq.event.city = new JSONgeoPoint();
        rq.event.city.id = 11l;
        rq.event.city.name = "***";

        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/events/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();

        String reply = result.getResponse().getContentAsString();
        EventListReply listReply = om.readValue(reply, EventListReply.class);
        assertEquals("Return code is not 0", listReply.retcode.longValue(), 0L);
        if (listReply.retcode == 0) {
            mockMvc.perform(get("/events/del/" + listReply.events.get(0).id)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )
                    .andExpect(status().isOk());

        }
    }
}