package com.myChef.repository;

import com.myChef.JPA.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart
 */
public interface RegionRepository extends CrudRepository<Region, Long> {
    @Override
    public List<Region> findAll();
}
