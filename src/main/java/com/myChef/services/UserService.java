/*
 * 
 * 
 */
package com.myChef.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cn.al.teach.library2.jpa.Appuser;
import ua.cn.al.teach.library2.jpa.Ugroup;
import ua.cn.al.teach.library2.jpa.Userdetails;
import ua.cn.al.teach.library2.repository.UserRepository;
import ua.cn.al.teach.library2.repository.UserdetailsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author al
 */

@Service
public class UserService {
private static final Logger logger =  LoggerFactory.getLogger(UserService.class);   

@Autowired
UserRepository userRepository;
@Autowired
UserdetailsRepository detailsRepository;
  
  public List<Appuser> getAllUsers(){
      return  userRepository.findAll();
  }

    public Appuser getUserById(Long id) {
        Appuser u = userRepository.findOne(id);
        return u;
    }
    
    public List<Appuser> findUserByName(String firstName, String lastName){
        List<Userdetails> udl = detailsRepository.findByFirstNameAndLastName(firstName, lastName);
        List<Appuser> res = new ArrayList<>();
        udl.forEach((ud) -> {
            res.add(userRepository.findOne(ud.getUserId()));
        });
        return res;
    }

    public Appuser addUser(Appuser au) {
        logger.debug("Adding users %s with id %s", au.getUsername(), au.getUserId());
        au = userRepository.save(au);
        return au;
    }

    public void delUser(Long id){
        Appuser u = userRepository.findOne(id);
        if(u!=null){
        logger.debug("Deleting users %s with id %s", u.getUsername(), u.getUserId());
           List<Ugroup> gl = u.getUgroupList();
           detailsRepository.delete(id);
           userRepository.delete(id);
        }
    }
}
