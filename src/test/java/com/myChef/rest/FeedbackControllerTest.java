package com.myChef.rest;

import api.feedback.AddFeedbackReply;
import api.feedback.FeedbackListReply;
import api.feedback.JSONfeedback;
import api.message.MessageListReply;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myChef.JPA.User;
import com.myChef.services.feedback.FeedbackService;
import com.myChef.services.user.UserService;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mocart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FeedbackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;


    @Test
    public void getFeedbacksForUser() throws Exception {
        MvcResult result = mockMvc.perform(get("/feedbacks/byuser/3"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String reply = result.getResponse().getContentAsString();

        ObjectMapper om = new ObjectMapper();
        FeedbackListReply listReply = om.readValue(reply, FeedbackListReply.class);

        assertTrue(listReply.error_message == null && listReply.feedbacks.size() == 2);
    }

    @Test
    public void addFeedback() throws Exception {
        AddFeedbackReply req = new AddFeedbackReply();
        req.feedback = new JSONfeedback();
        req.feedback.fromUserFirstName = "Ivan";
        req.feedback.fromUserID = 1;
        req.feedback.toUserID = 4;
        req.feedback.grade = 10;
        req.feedback.description = "test";

        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(req);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/feedbacks/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn();

        String reply = mvcResult.getResponse().getContentAsString();
        FeedbackListReply listReply = om.readValue(reply, FeedbackListReply.class);

        assertEquals("Return code is not 0", 0l, listReply.retcode.longValue());


        req = new AddFeedbackReply();
        req.feedback = new JSONfeedback();
        req.feedback.fromUserFirstName = "Ivan";
        req.feedback.fromUserID = 2;
        req.feedback.toUserID = 4;
        req.feedback.grade = 2;
        req.feedback.description = "test";

        om = new ObjectMapper();
        content = om.writeValueAsString(req);

         mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/feedbacks/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn();

        reply = mvcResult.getResponse().getContentAsString();
        FeedbackListReply newListReply = om.readValue(reply, FeedbackListReply.class);

        assertTrue ("The grade is incorrect", userService.getUserById(4l).getUserDetails().getGrade() == 6);

        feedbackService.delFeedback(listReply.feedbacks.get(0).id);
        feedbackService.delFeedback(newListReply.feedbacks.get(0).id);

        User user = userService.getUserById(4l);
        user.getUserDetails().setGradeCounter(0);
        user.getUserDetails().setGrade(0);
        userService.updateUser(user);
    }

}