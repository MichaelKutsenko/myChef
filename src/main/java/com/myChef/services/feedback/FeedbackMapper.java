package com.myChef.services.feedback;

import api.feedback.JSONfeedback;
import com.myChef.JPA.Feedback;
import com.myChef.repository.FeedbackRepository;
import com.myChef.repository.UserDetailsRepository;
import com.myChef.utils.EntityIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mocart
 */
@Component
public class FeedbackMapper {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackMapper.class);

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserDetailsRepository detailsRepository;

    /**
     * Maps internal JPA model to external REST model
     * @param feedback internal feedback model
     * @return JSONfeedback external REST model of Feedback
     */
    public JSONfeedback fromInternal(Feedback feedback) {
        JSONfeedback json = null;
        if (feedback != null) {
            json = new JSONfeedback();

            json.id = feedback.getFeedbackId();
            json.fromUserID = feedback.getFromUser().getUserId();
            json.fromUserFirstName = feedback.getFromUser().getFirstName();
            json.toUserID = feedback.getToUser().getUserId();
            json.description = feedback.getDescription();
            json.grade = feedback.getGrade();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            json.date = sdf.format(feedback.getDate());
        }
        return json;
    }

    /**
     * Maps external REST model to internal Feedback;
     * If event does not exist in DB then creates new. If event already exists
     * then fetches event from DB and sets all fields from external REST model
     * @param json REST model
     * @return internal Feedback with all required fields set
     */


    public Feedback toInternal(JSONfeedback json) {
        Feedback feedback = null;
        //first, check if it exists
        if (json.id != null) {
            feedback = feedbackRepository.findOne(json.id);
        }
        //not found, create new
        if (feedback == null) {
            logger.debug(String.format("Creating new feedback to user with id %s from user %s",
                    json.toUserID, json.fromUserID));

            feedback = new Feedback();

            Long id = 0L;
            boolean idOK = false;
            while (!idOK) {
                id = EntityIdGenerator.random();
                idOK = !feedbackRepository.exists(id);
            }
            feedback.setFeedbackId(id);
        }
        else {
            logger.debug("Updating existing feedback");
        }

        feedback.setFromUser(detailsRepository.findOne(json.fromUserID));
        feedback.setToUser(detailsRepository.findOne(json.toUserID));
        feedback.setDescription(json.description);
        feedback.setGrade(json.grade);
        feedback.setDate(new Date());

        return feedback;
    }
}
