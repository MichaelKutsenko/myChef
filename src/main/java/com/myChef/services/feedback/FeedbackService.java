package com.myChef.services.feedback;

import com.myChef.JPA.Event;
import com.myChef.JPA.Feedback;
import com.myChef.repository.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mocart
 */
@Service
public class FeedbackService {
    private static final Logger logger =  LoggerFactory.getLogger(FeedbackService.class);

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getFeedbacskForUser(long toUserID){
        List<Feedback> feedbacks = feedbackRepository.findByToUser_UserId(toUserID);

        Collections.sort(feedbacks);
        return feedbacks;
    }

    public Feedback addFeedback(Feedback feedback) {
        logger.debug("Adding Feedback with id %s to user with id %s", feedback.getFeedbackId(),feedback.getToUser().getUserId());

        feedback = feedbackRepository.save(feedback);
        return feedback;
    }

    public List<Feedback> getFeedbacksBeetwenUsers(long toUserID, long fromUserID){
        return feedbackRepository.findByToUser_UserIdAndFromUser_userId(toUserID, fromUserID);
    }

    public void delFeedback(long id) {
        Feedback feedback = feedbackRepository.findOne(id);
        if (feedback != null) {
            logger.debug("Deleting feedback with id %s",  feedback.getFeedbackId());

            feedbackRepository.delete(id);
        }
    }
}
