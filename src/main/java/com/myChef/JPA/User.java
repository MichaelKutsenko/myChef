package com.myChef.JPA;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

/**
 * Created by Mocart on 07-Mar-17.
 */
@Entity
@Table(name = "users", schema = "my_chef_db")
public class User implements Serializable {
    private long userId;
    private String userName;
    private String email;
    private String pswrdHash;
    private UserDetails userDetails;
    private ChefDetails chefDetails;
    private List<Ugroup> ugroups = new ArrayList<>();

    @Id
    @Column(name = "user_id")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "pswrd_hash")
    public String getPswrdHash() {
        return pswrdHash;
    }

    public void setPswrdHash(String pswrdHash) {
        this.pswrdHash = pswrdHash;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    public ChefDetails getChefDetails() {
        return chefDetails;
    }

    public void setChefDetails(ChefDetails chefDetails) {
        this.chefDetails = chefDetails;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @JoinTable(name = "user_group",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "group_id", referencedColumnName = "group_id")})
    @ManyToMany(cascade = CascadeType.DETACH, fetch=EAGER)
    public List<Ugroup> getUgroups() {
        return ugroups;
    }

    public void setUgroups(List<Ugroup> ugroups) {
        this.ugroups = ugroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (pswrdHash != null ? !pswrdHash.equals(user.pswrdHash) : user.pswrdHash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pswrdHash != null ? pswrdHash.hashCode() : 0);
        return result;
    }

    public void addGroup (Ugroup group) {
        if (!ugroups.contains(group)){
            ugroups.add(group);
        }
    }

    public void deleteGroup(Ugroup group) {
        group.getUsers().remove(this);
        ugroups.remove(group);
    }

}
