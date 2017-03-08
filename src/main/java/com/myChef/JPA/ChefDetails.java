package com.myChef.JPA;

import javax.persistence.*;

/**
 * Created by Mocart on 07-Mar-17.
 */
@Entity
@Table(name = "chef_details", schema = "my_chef_db")
public class ChefDetails {
    private long userId;
    private int pricePerHour;
    private int minPrice;
    private String description;
    private User user;

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "price_per_hour")
    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    @Basic
    @Column(name = "min_price")
    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChefDetails that = (ChefDetails) o;

        if (minPrice != that.minPrice || pricePerHour != that.pricePerHour) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pricePerHour ^ (pricePerHour >>> 32);
        result = 31 * result + minPrice;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
