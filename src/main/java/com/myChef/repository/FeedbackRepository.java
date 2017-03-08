package com.myChef.repository;

import com.myChef.JPA.Feedback;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart
 */
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    @Override
    public List<Feedback> findAll();

}
