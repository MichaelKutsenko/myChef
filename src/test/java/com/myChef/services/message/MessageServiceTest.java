package com.myChef.services.message;

import com.myChef.JPA.Message;
import com.myChef.repository.EventRepository;
import com.myChef.repository.MessageRepository;
import com.myChef.repository.UserDetailsRepository;
import com.myChef.utils.EntityIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Mocart
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserDetailsRepository detailsRepository;

    @Test
    public void getMessagesByEvent() throws Exception {
        assert (messageService.getMessagesByEvent(2).size() == 2);
    }

    @Test
    public void addMessage() throws Exception {
        long id = EntityIdGenerator.random();

        while (messageRepository.exists(id)) {
            id = EntityIdGenerator.random();
        }

        Message message = new Message();

        message.setMessageId(id);
        message.setEvent(eventRepository.findOne(5l));
        message.setUser(detailsRepository.findOne(4l));
        message.setDate(new Date());
        message.setDescription("test");

        messageService.addMessage(message);

        assertEquals("messages are nor equal", message, messageRepository.findOne(id));

        messageRepository.delete(id);

    }

}