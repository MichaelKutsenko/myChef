package com.myChef.services.message;

import com.myChef.JPA.Message;
import com.myChef.repository.MessageRepository;
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
public class MessageService {
    private static final Logger logger =  LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessagesByEvent(long eventID){
        List<Message> messages = messageRepository.findByEvent_EventId(eventID);

        Collections.sort(messages);
        return messages;
    }

    public Message addMessage(Message message) {
        logger.debug("Adding Message with id %s to event with id %s", message.getMessageId(), message.getEvent().getEventId());
        message = messageRepository.save(message);
        return message;
    }
}
