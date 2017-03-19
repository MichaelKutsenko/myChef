package com.myChef.repository;

import com.myChef.JPA.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Override
    public List<Message> findAll();

    public List<Message> findByEvent_EventId(long eventID);
}
