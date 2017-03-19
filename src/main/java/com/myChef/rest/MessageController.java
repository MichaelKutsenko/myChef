package com.myChef.rest;

import api.message.AddMessageRequest;
import api.message.MessageListReply;
import com.myChef.JPA.Message;
import com.myChef.repository.MessageRepository;
import com.myChef.services.message.MessageMapper;
import com.myChef.services.message.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mocart
 */
@RestController
public class MessageController {
    private static final Logger logger =  LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageMapper messageMapper;

    @RequestMapping(path="/messages/byevent/{eventID}",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MessageListReply getMessagesByEvent(@PathVariable long eventID ){
        MessageListReply reply = new MessageListReply();

        for(Message message: messageService.getMessagesByEvent(eventID)){
            reply.messages.add(messageMapper.fromInternal(message));
        }
        return reply;
    }

    @RequestMapping(path="/messages/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MessageListReply addMessage( @RequestBody AddMessageRequest req){
        MessageListReply rep = new MessageListReply();
        try{
            Message message = messageService.addMessage(messageMapper.toInternal(req.message));
            rep.messages.add(messageMapper.fromInternal(message));
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding new message. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }

}
