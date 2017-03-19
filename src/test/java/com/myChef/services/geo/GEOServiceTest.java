package com.myChef.services.geo;

import com.myChef.JPA.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Mocart
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GEOServiceTest {
    @Autowired
    private GEOService service;

    @Test
    public void getAllRegions() throws Exception {
        int count = service.getAllRegions().size();
        assert(count == 25);
    }

    @Test
    public void getCitiesByRegion() throws Exception {
        List<City> cities = service.getCitiesByRegion(23);
        Set<String> names = new HashSet<>();

        for (City city: cities){
            names.add(city.getCityName());
        }

        assertTrue(names.contains("Чернигов"));
    }

}