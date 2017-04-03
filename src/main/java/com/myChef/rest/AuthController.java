
package com.myChef.rest;

import api.login.LoginReply;
import api.login.LoginRequest;
import com.myChef.JPA.User;
import com.myChef.security.AuthUser;
import com.myChef.security.TokenProvider;
import com.myChef.services.user.UserMapper;
import com.myChef.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by Mocart
 */
@RestController
public class AuthController {
private static final Logger logger =  LoggerFactory.getLogger(AuthController.class);
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenProvider tokenProvider;

    @RequestMapping(path = "/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public LoginReply authUser(@RequestBody LoginRequest req) {
        LoginReply rep = new LoginReply();
        User user;
        user = userService.authUser(req.login, req.password);

        if (user != null) {
            String token = tokenProvider.newToken();
            tokenProvider.put(token, new AuthUser(user));
            rep.user = userMapper.fromInternal(user);
            rep.token = token;
        } else {
            logger.error("Error loggin in user. User: " + req.login);
        }
        return rep;
    }

}
