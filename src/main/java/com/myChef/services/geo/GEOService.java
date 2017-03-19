package com.myChef.services.geo;

import com.myChef.JPA.City;
import com.myChef.JPA.Region;
import com.myChef.repository.CityRepository;
import com.myChef.repository.RegionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mocart
 */
@Service
public class GEOService {
    private static final Logger logger =  LoggerFactory.getLogger(GEOService.class);

    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CityRepository cityRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public List<City> getCitiesByRegion(long regionID){
        return cityRepository.findByRegion_RegionId(regionID);
    }

    public City getCityById (long cityID){
        return cityRepository.findOne(cityID);
    }


}
