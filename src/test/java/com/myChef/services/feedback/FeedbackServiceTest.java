package com.myChef.services.feedback;

import com.myChef.JPA.Feedback;
import com.myChef.services.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.ref.PhantomReference;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mocart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeedbackServiceTest {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;

    @Test
    public void getFeedbacskForUser() throws Exception {
        List<Feedback> list = feedbackService.getFeedbacskForUser(4l);

        assert (list.size() == 2);
    }

    @Test
    public void addFeedback() throws Exception {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(100l);
        feedback.setDate(new Date());
        feedback.setDescription("test");
        feedback.setGrade(10);
        feedback.setFromUser(userService.getUserById(1l).getUserDetails());
        feedback.setToUser(userService.getUserById(5l).getUserDetails());

        feedbackService.addFeedback(feedback);

        Feedback actual = feedbackService.getFeedbacskForUser(5l).get(0);

        assertEquals("Feedbacks are not equal", feedback, actual);

        feedbackService.delFeedback(100l);
    }
}