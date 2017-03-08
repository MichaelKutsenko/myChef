package com.myChef.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mocart on 07-Mar-17.
 */
@Entity
@Table(name = "regions", schema = "my_chef_db")
public class Region {
    private long regionId;
    private String regionName;
    private List<City> cities = new ArrayList<>();

    @Id
    @Column(name = "region_id")
    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "region_name")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @OneToMany(mappedBy = "region")
    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (regionId != region.regionId) return false;
        if (regionName != null ? !regionName.equals(region.regionName) : region.regionName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (regionId ^ (regionId >>> 32));
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        return result;
    }

    public void addCity (City city){
        cities.add(city);
        if (city.getRegion() != this) city.setRegion(this);
    }
}
