package com.myChef.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Mocart
 */
public class UserAuthority implements GrantedAuthority {
    private String autority;


    public UserAuthority(Authority groupName) {
        autority = groupName.name();
    }

    public UserAuthority(String groupName) {
        autority = groupName;
    }

    @Override
    public String getAuthority() {
        return autority;
    }
}
