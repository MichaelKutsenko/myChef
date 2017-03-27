/*
 * 
 * 
 */
package com.myChef.services.user;

import com.myChef.JPA.ChefDetails;
import com.myChef.JPA.User;
import com.myChef.JPA.UserDetails;
import com.myChef.repository.ChefDetailsRepository;
import com.myChef.repository.UserDetailsRepository;
import com.myChef.repository.UserRepository;
import com.myChef.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author al
 */

@Service
public class UserService implements UserDetailsService {
private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository detailsRepository;
    @Autowired
    private ChefDetailsRepository chefDetailsRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public List<User> getAllChefs() {
        List<User> users = new ArrayList<>();

        for (ChefDetails chefDetails : chefDetailsRepository.findAll()){
            users.add(chefDetails.getUser());
        }

        return users;
    }

    public List<User> getChefsByCity(long cityID) {
        List<User> users = new ArrayList<>();

        for (UserDetails userDetails : detailsRepository.findByCity_CityIdAndChefTrue(cityID)){
            users.add(userDetails.getUser());
        }

        return users;
    }

    public User getUserById(Long id) {
        User user = userRepository.findOne(id);
        return user;
    }

    public User addUser(User user) {
        logger.debug("Adding user %s with id %s", user.getUserName(), user.getUserId());
        user = userRepository.save(user);
        return user;
    }

    public User updateUser(User user) {
        logger.debug("Updating user %s with id %s", user.getUserName(), user.getUserId());
        user = userRepository.save(user);
        return user;
    }

    public void delUser(Long id) {
        User user = userRepository.findOne(id);
        if (user != null) {
            logger.debug("Deleting users %s with id %s", user.getUserName(), user.getUserId());

            if (user.getChefDetails() != null) {
                chefDetailsRepository.delete(id);
            }
            detailsRepository.delete(id);
            userRepository.delete(id);
        }
    }

    //for Spring Security
    public User authUser(String login, String password) {
        User user = userRepository.findByUserName(login);

        if (user != null) {
            if( ! user.getPswrdHash().equalsIgnoreCase(digest(password))){
                user = null;
                logger.debug("Invalid password");
            }
            logger.debug("Login ok");
        }else{
            logger.debug("User not found");
        }
        return user;
    }

    public static String digest(String original) {
        String res = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(original.getBytes());
            byte[] digest = md.digest();
            res = new String(Hex.encode(digest));
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Can not create SHA-256 digester");
        }
        return res;
    }

    @Override
    public AuthUser loadUserByUsername(String login) throws UsernameNotFoundException {
        return new AuthUser(userRepository.findByUserName(login));
    }
}
