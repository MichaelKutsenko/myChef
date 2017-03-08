package com.myChef.services;

import com.myChef.JPA.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mocart
 */

@Service
public class CityService {
    public List<City> getAll() {
        List<City> bl = new ArrayList<>();
        return bl;
    }
}
