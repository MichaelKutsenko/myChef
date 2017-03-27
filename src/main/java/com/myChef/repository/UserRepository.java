package com.myChef.repository;

import com.myChef.JPA.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart
 */
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    public List<User> findAll();

    public User findByUserName(String username);
}
