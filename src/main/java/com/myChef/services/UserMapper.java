package com.myChef.services;


import api.JSONchef;
import api.JSONuser;
import com.myChef.JPA.*;
import com.myChef.repository.CityRepository;
import com.myChef.repository.UgroupRepository;
import com.myChef.repository.UserDetailsRepository;
import com.myChef.repository.UserRepository;
import com.myChef.utils.EntityIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * Created by Mocart
 */

@Component
public class UserMapper {
    private static final Logger logger =  LoggerFactory.getLogger(UserMapper.class);

    private static final Long CHEF_GROUP_ID = 1L;
    private static final Long USER_GROUP_ID = 2L;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository detailsRepository;
    @Autowired
    private UgroupRepository groupRepository;
    @Autowired
    private CityRepository cityRepository;

    /**
     * Maps internal JPA model to external REST model
     * @param user innternal user model
     * @return external REST user model
     */
    public JSONuser fromInternal(User user) {
        JSONuser json = null;
        if (user != null) {
            json = new JSONuser();
            UserDetails ud = user.getUserDetails();
            ChefDetails cd = user.getChefDetails();

            json.id = user.getUserId();
            json.login = user.getUserName();
            json.email = user.getEmail();
            json.firstName = ud.getFirstName();
            json.lastName = ud.getLastName();
            json.city_id = ud.getCity().getCityId();
            json.grade = ud.getGrade();
            json.gradeCouner = ud.getGradeCounter();
            json.phone = ud.getPhone();
            json.isChef = ud.isChef();

            if (ud.isChef() == true){
                JSONchef chef = new JSONchef();

                chef.minPrice = cd.getMinPrice();
                chef.pricePerHour = cd.getPricePerHour();
                chef.description = cd.getDescription();

                json.chef = chef;
            }
        }
        return json;
    }

    /**
     * Maps extrernal REST model to internal User;
     * If user does not exist in DB then creates new. If user already exists
     * then fetches user from DB and sets all fields from external REST model
     * @param json REST model
     * @return internal User with all required fields set
     */
    public User toInternal(JSONuser json) {
        User user = null;
        //first, check if it exists
        if (json.id != null) {
            user = userRepository.findOne(json.id);
        }
        if (user == null) { //not found, create new
            logger.debug("Creating new user");
            user = newUser(json.isChef);
        }
        logger.debug("Updating existing user");
        user.setUserName(json.login);
        user.setEmail(json.email);

        user.getUserDetails().setFirstName(json.firstName);
        user.getUserDetails().setLastName(json.lastName);
        user.getUserDetails().setPhone(json.phone);
        user.getUserDetails().setCity(cityRepository.findOne(json.city_id));
        user.getUserDetails().setChef(json.isChef);

        Ugroup group;
        if (json.isChef) {
            user.getChefDetails().setMinPrice(json.chef.minPrice);
            user.getChefDetails().setPricePerHour(json.chef.pricePerHour);
            user.getChefDetails().setDescription(json.chef.description);

            group = groupRepository.findOne(CHEF_GROUP_ID);
        }
        else {
            group = groupRepository.findOne(USER_GROUP_ID);
        }
//        group.getUsers().add(user);
//        user.getUgroups().add(group);
        user.addGroup(group);

        return user;
    }


    /**
     * Creates new User, Userdeatils and ChefDetails with good Id
     * @return newly created Appuser with required fields set
     */
    private User newUser(boolean isChef) {
        User user = new User();
        UserDetails ud = new UserDetails();

        Long id = 0L;
        boolean idOK = false;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !userRepository.exists(id);
        }

        user.setPswrdHash("111111");
        user.setUserId(id);
        ud.setUserId(id);
        user.setUserDetails(ud);

        if (isChef) {
            ChefDetails cd = new ChefDetails();
            cd.setUserId(id);
            user.setChefDetails(cd);
        }

        return user;
    }

}
