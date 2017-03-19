package com.myChef.services.message;

import api.message.JSONmessage;
import com.myChef.JPA.Message;
import com.myChef.repository.EventRepository;
import com.myChef.repository.MessageRepository;
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
public class MessageMapper {
    private static final Logger logger =  LoggerFactory.getLogger(MessageMapper.class);

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserDetailsRepository detailsRepository;
    @Autowired
    private EventRepository eventRepository;

    /**
     * Maps internal JPA model to external REST model
     * @param message innternal message model
     * @return external REST message model
     */
    public JSONmessage fromInternal(Message message) {
        JSONmessage json = null;
        if (message != null) {
            json = new JSONmessage();

            json.id = message.getMessageId();
            json.eventID = message.getEvent().getEventId();
            json.userFirstName = message.getUser().getFirstName();
            json.userID = message.getUser().getUserId();
            json.description = message.getDescription();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            json.date = sdf.format(message.getDate());
        }
        return json;
    }

    /**
     * Maps extrernal REST model to internal Message;
     * If event does not exist in DB then creates new. If event already exists
     * then fetches event from DB and sets all fields from external REST model
     * @param json REST model
     * @return internal Message with all required fields set
     */
    public Message toInternal(JSONmessage json) {
        Message message = null;
        //first, check if it exists
        if (json.id != null) {
            message = messageRepository.findOne(json.id);
        }
        //not found, create new
        if (message == null) {
            logger.debug("Creating new message");

            message = new Message();

            Long id = 0L;
            boolean idOK = false;
            while (!idOK) {
                id = EntityIdGenerator.random();
                idOK = !messageRepository.exists(id);
            }
            message.setMessageId(id);
        }
        else {
            logger.debug("Updating existing event");
        }

        message.setUser(detailsRepository.findOne(json.userID));
        message.setEvent(eventRepository.findOne(json.eventID));
        message.setDescription(json.description);
        message.setDate(new Date());

        return message;
    }
}
