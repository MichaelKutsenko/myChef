package com.myChef.rest;

import api.geo.GEOListReply;
import com.myChef.JPA.City;
import com.myChef.JPA.Region;
import com.myChef.services.geo.GEOMapper;
import com.myChef.services.geo.GEOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mocart
 */
@RestController
public class GEOController {
    private static final Logger logger =  LoggerFactory.getLogger(GEOController.class);

    @Autowired
    private GEOService geoService;
    @Autowired
    private GEOMapper geoMapper;

    @RequestMapping(path="/regions/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GEOListReply getAllRegions(){
        GEOListReply reply = new GEOListReply();

        for(Region region: geoService.getAllRegions()){
            reply.geoPoints.add(geoMapper.fromInternal(region));
        }
        return reply;
    }


    @RequestMapping(path="/cities/byregion/{regionID}",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GEOListReply getCitiesByRegion(@PathVariable long regionID){
        GEOListReply reply = new GEOListReply();

        for(City city: geoService.getCitiesByRegion(regionID)){
            reply.geoPoints.add(geoMapper.fromInternal(city));
        }
        return reply;
    }
}
