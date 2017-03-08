package com.myChef.repository;

import com.myChef.JPA.ChefDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart
 */
public interface ChefDetailsRepository extends CrudRepository<ChefDetails, Long> {
    @Override
    public List<ChefDetails> findAll();
}
