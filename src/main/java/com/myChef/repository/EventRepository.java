package com.myChef.repository;

import com.myChef.JPA.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart
 */
public interface EventRepository extends CrudRepository<Event, Long> {
    @Override
    public List<Event> findAll();
}
