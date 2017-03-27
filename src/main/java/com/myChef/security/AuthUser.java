package com.myChef.security;

import com.myChef.JPA.Ugroup;
import com.myChef.JPA.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mocart
 */
public class AuthUser implements UserDetails {
    private User user;
    private Collection<UserAuthority> authorities = new ArrayList<>();

    public AuthUser(User user){
        this.user = user;

//        authorities.add(new UserAuthority("USER"));
        List<Ugroup> groups = user.getUgroups();

        for (Ugroup group : groups) {
            authorities.add(new UserAuthority(group.getGroupName()));
        }
    }

    public User getAppUser(){
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPswrdHash();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
