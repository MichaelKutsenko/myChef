package com.myChef.rest;

import com.myChef.JPA.City;
import com.myChef.services.CityMapper;
import com.myChef.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mocart
 */

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @Autowired
    CityMapper cityMapper;

    @RequestMapping(path="/cities/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CityReply getAllCities(){
        CityReply reply = new CityReply();

        for(City city: cityService.getAll()){
            reply.cities.add(cityMapper.fromInternal(city));
        }
        return reply;
    }
}
