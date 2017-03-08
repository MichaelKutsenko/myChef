package com.myChef.repository;

import com.myChef.JPA.Ugroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart
 */
public interface UgroupRepository extends CrudRepository<Ugroup, Long> {
    @Override
    public List<Ugroup> findAll();
}
