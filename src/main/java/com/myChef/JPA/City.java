package com.myChef.JPA;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mocart on 07-Mar-17.
 */
@Entity
@Table(name = "cities", schema = "my_chef_db")
public class City extends GEOPoint  implements Serializable {
    private long cityId;
    private String cityName;
    private boolean isCenter;
    private Region region;
    private Collection<Event> events = new ArrayList<>();
    private Collection<UserDetails> users = new ArrayList<>();

    @Id
    @Column(name = "city_id")
    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "is_center")
    public boolean isCenter() {
        return isCenter;
    }

    public void setCenter(boolean center) {
        isCenter = center;
    }

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "region_id", nullable = false)
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @OneToMany(mappedBy = "city")
    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    @OneToMany(mappedBy = "city")
    public Collection<UserDetails> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserDetails> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (cityId != city.cityId) return false;
        if (isCenter != city.isCenter) return false;
        if (cityName != null ? !cityName.equals(city.cityName) : city.cityName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cityId ^ (cityId >>> 32));
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (isCenter ? 1 : 0);
        return result;
    }


    public void addUser (UserDetails userDetails){
        users.add(userDetails);
        if (userDetails.getCity() != this) userDetails.setCity(this);
    }

    public void addEvent (Event event){
        events.add(event);
        if (event.getCity() != this) event.setCity(this);
    }
}
