package com.myChef.rest;

import api.feedback.AddFeedbackReply;
import api.feedback.FeedbackListReply;
import com.myChef.JPA.Feedback;
import com.myChef.JPA.User;
import com.myChef.services.feedback.FeedbackMapper;
import com.myChef.services.feedback.FeedbackService;
import com.myChef.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mocart
 */
@RestController
public class FeedbackController {
    private static final Logger logger =  LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private UserService userService;

    @RequestMapping(path="/feedbacks/byuser/{userID}",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FeedbackListReply getFeedbacksForUser(@PathVariable long userID ){
        FeedbackListReply reply = new FeedbackListReply();

        for(Feedback feedback: feedbackService.getFeedbacskForUser(userID)){
            reply.feedbacks.add(feedbackMapper.fromInternal(feedback));
        }
        return reply;
    }

    @RequestMapping(path="/feedbacks/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FeedbackListReply addFeedback( @RequestBody AddFeedbackReply req){
        FeedbackListReply rep = new FeedbackListReply();
        try{
            Feedback feedback = feedbackService.addFeedback(feedbackMapper.toInternal(req.feedback));
            rep.feedbacks.add(feedbackMapper.fromInternal(feedback));


            double grade = feedback.getGrade(); //current grade from user
            int averageGrade = 0; //average grade from specify user to another specify user
            int temp = 0; //Sum
            int counter = 0; //number of grades

            User user = userService.getUserById(req.feedback.toUserID);
            double userGrade = user.getUserDetails().getGrade();
            int userGradeCounter = user.getUserDetails().getGradeCounter();

            List<Feedback> list = feedbackService.getFeedbacksBeetwenUsers(req.feedback.toUserID, req.feedback.fromUserID);
            if (list.size() > 1){
                for (Feedback f: list){
                    temp += f.getGrade();
                    counter++;
                }
                averageGrade = temp/counter;

                userGrade = (userGrade*userGradeCounter - averageGrade + ((temp +  grade)/(counter+1)))/userGradeCounter;
                user.getUserDetails().setGrade(userGrade);
            }
            else {

                userGrade = (userGrade*userGradeCounter + grade)/(userGradeCounter + 1);
                user.getUserDetails().setGrade(userGrade);
                user.getUserDetails().setGradeCounter(userGradeCounter + 1);
            }

            userService.updateUser(user);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding feedback. Expetion: "+e.getMessage(),e);
            e.printStackTrace();
        }
        return rep;
    }
}
