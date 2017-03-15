package com.myChef.services;

import com.myChef.JPA.Ugroup;
import com.myChef.JPA.User;
import com.myChef.JPA.UserDetails;
import com.myChef.repository.CityRepository;
import com.myChef.repository.UgroupRepository;
import com.myChef.utils.EntityIdGenerator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.acl.Group;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Mocart
 */

@FixMethodOrder
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UgroupRepository ugroupRepository;

    @Test
    public void test001_getAllUsers() throws Exception {
        Set<String> names = new HashSet();

        for (User user : userService.getAllUsers()){
            names.add(user.getUserName());
        }

        assertTrue(names.contains("Anna") && names.contains("Ivan") && names.contains("Oleg") &&
                names.contains("Olya") && names.contains("Manya"));
    }

    @Test
    public void test002_getAllChefs() throws Exception {
        Set<String> names = new HashSet();

        for (User user : userService.getAllChefs()){
            names.add(user.getUserName());
        }

        assertTrue("Can not find all chefs.", names.contains("Oleg") && names.contains("Olya") && names.contains("Manya"));
    }

    @Test
    public void test003_getChefsByCity() throws Exception {
        Long id = 2l;
        Set<String> names = new HashSet();

        for (User user : userService.getChefsByCity(id)){
            names.add(user.getUserName());
        }

        assertTrue("Can not find chefs by city's id.", names.contains("Oleg") && names.contains("Manya"));
    }

    @Test
    public void test004_getUserById() throws Exception {
        String expected = "Manya";
        String actual = userService.getUserById(5l).getUserName();
        assertEquals("Can not find user by id.", expected, actual);
    }

    @Test
    public void test005_addUser() throws Exception {
        Long user_id = EntityIdGenerator.random();

        User user = new User();
        user.setUserId(user_id);
        user.setUserName("test");
        user.setEmail("email");
        user.setPswrdHash("*****");

        UserDetails details = new UserDetails();
        details.setUserId(user_id);
        details.setCity(cityRepository.findOne(2l));
        details.setFirstName("TEST");
        details.setLastName("text_TEST");
        details.setPhone("1111111");

        Ugroup group = ugroupRepository.findOne(2l);
        user.addGroup(group);

        user.setUserDetails(details);

        userService.addUser(user);

        User u = userService.getUserById(user_id);

        assertNotNull("New user not found", u);
        assertEquals("Users are different", u, user);

        userService.delUser(user_id);
        u = userService.getUserById(user_id);
        assertNull("Can not delete user", u);

    }

    @Test
    public void test006_updateUser() throws Exception {
        Long user_id = EntityIdGenerator.random();

        User user = new User();
        user.setUserId(user_id);
        user.setUserName("test");
        user.setEmail("email");
        user.setPswrdHash("*****");

        UserDetails details = new UserDetails();
        details.setUserId(user_id);
        details.setCity(cityRepository.findOne(2l));
        details.setFirstName("TEST");
        details.setLastName("text_TEST");
        details.setPhone("1111111");

        user.setUserDetails(details);

        userService.addUser(user);

        details.setCity(cityRepository.findOne(1l));
        userService.updateUser(user);

        User u = userService.getUserById(user_id);
        assertEquals("City was not changed.", "Винница",
                u.getUserDetails().getCity().getCityName());

        userService.delUser(user_id);
        u = userService.getUserById(user_id);
        assertNull("Can not delete user", u);
    }

}