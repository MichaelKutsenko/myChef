package com.myChef.repository;

import com.myChef.JPA.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart
 */
public interface CityRepository extends CrudRepository<City, Long> {
    @Override
    public List<City> findAll();
}
