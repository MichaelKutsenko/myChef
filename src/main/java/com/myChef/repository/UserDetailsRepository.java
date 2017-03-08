package com.myChef.repository;

import com.myChef.JPA.UserDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart
 */
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
    @Override
    public List<UserDetails> findAll();

    public List<UserDetails> findByFirstNameAndLastName(String firstName, String lastName);

}
