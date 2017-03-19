package com.myChef.rest;

import api.message.AddMessageRequest;
import api.message.JSONmessage;
import api.message.MessageListReply;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myChef.repository.MessageRepository;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mocart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MessageRepository messageRepository;


    @Test
    public void getMessagesByEvent() throws Exception {
        MvcResult result = mockMvc.perform(get("/messages/byevent/2"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String reply = result.getResponse().getContentAsString();

        ObjectMapper om = new ObjectMapper();
        MessageListReply listReply = om.readValue(reply, MessageListReply.class);

        assertTrue(listReply.error_message == null && listReply.messages.size() == 2);
    }

    @Test
    public void addMessage() throws Exception {
        AddMessageRequest req = new AddMessageRequest();
        req.message = new JSONmessage();
        req.message.userID = 1;
        req.message.userFirstName = "Ivan";
        req.message.eventID = 5;
        req.message.description = "test";

        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(req);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/messages/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String reply = mvcResult.getResponse().getContentAsString();
        MessageListReply listReply = om.readValue(reply, MessageListReply.class);

        assertEquals("Return code is not 0", 0l, listReply.retcode.longValue());
        messageRepository.delete(listReply.messages.get(0).id);
    }

}