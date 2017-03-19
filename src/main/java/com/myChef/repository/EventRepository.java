package com.myChef.repository;

import com.myChef.JPA.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Mocart
 */
public interface EventRepository extends CrudRepository<Event, Long> {
    public List<Event> findByEventDateAfter(Date today);

    public List<Event> findByCity_CityIdAndEventDateAfter(long cityID, Date today);
}
