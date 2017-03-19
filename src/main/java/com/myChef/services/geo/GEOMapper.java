package com.myChef.services.geo;

import api.geo.JSONgeoPoint;
import com.myChef.JPA.City;
import com.myChef.JPA.GEOPoint;
import com.myChef.JPA.Region;
import org.springframework.stereotype.Component;

/**
 * Created by Mocart
 */
@Component
public class GEOMapper {

    /**
     * Maps internal JPA model to external REST model
     *
     * @param geoPoint internal geoPoint model
     * @return external REST geoPoint model
     */
    public JSONgeoPoint fromInternal(GEOPoint geoPoint) {
        JSONgeoPoint json = null;

        if (geoPoint != null) {
            json = new JSONgeoPoint();

            if (geoPoint instanceof Region){
                Region region = (Region) geoPoint;

                json.id = region.getRegionId();
                json.name = region.getRegionName();

            }
            else if (geoPoint instanceof City){
                City city = (City) geoPoint;

                json.id = city.getCityId();
                json.name = city.getCityName();
            }
        }

        return json;
    }
}
