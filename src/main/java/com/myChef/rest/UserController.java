/*
 * 
 * 
 */
package com.myChef.rest;

import api.user.AddUserRequest;
import api.GenericReply;
import api.user.UserListReply;
import com.myChef.JPA.User;
import com.myChef.services.user.UserMapper;
import com.myChef.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Created by Mocart
 */

@RestController
public class UserController {
    private static final Logger logger =  LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @RequestMapping(path="/users/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getAllUsers(){
        UserListReply reply = new UserListReply();

        for(User user: userService.getAllUsers()){
            reply.users.add(userMapper.fromInternal(user));
        }
        return reply;
    }

    @RequestMapping(path="/users/chefs/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getAllChefs(){
        UserListReply reply = new UserListReply();

        for(User user: userService.getAllChefs()){
            reply.users.add(userMapper.fromInternal(user));
        }
        return reply;
    }

    @RequestMapping(path="/users/chefs/bycity/{cityID}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getChefsByCity(@PathVariable long cityID ){
        UserListReply reply = new UserListReply();

        for(User user: userService.getChefsByCity(cityID)){
            reply.users.add(userMapper.fromInternal(user));
        }
        return reply;
    }

    @RequestMapping(path="/users/byid/{userid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply getUserById(@PathVariable Long userid ){
        UserListReply reply = new UserListReply();

        reply.users.add(userMapper.fromInternal(userService.getUserById(userid)));
        return reply;
    }

    @RequestMapping(path="/users/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply addUser( @RequestBody AddUserRequest req){
        UserListReply rep = new UserListReply();
        try{
            User user = userService.addUser(userMapper.toInternal(req.user));
            rep.users.add(userMapper.fromInternal(user));
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error adding user. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }

    @RequestMapping(path="/users/update",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply updateUser( @RequestBody AddUserRequest req){
        UserListReply rep = new UserListReply();
        try{
            User user = userService.updateUser(userMapper.toInternal(req.user));
            rep.users.add(userMapper.fromInternal(user));
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error updating user. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }

    @RequestMapping(path="/users/del/{userid}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delUser(@PathVariable Long userid ){
        GenericReply rep = new GenericReply();
        try{
            userService.delUser(userid);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.error("Error deleting user. Expetion: "+e.getMessage(),e);
        }
        return rep;
    }
}
