package com.myChef.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Mocart
 */
public class UserAuthority implements GrantedAuthority {
    private String authority;

    public UserAuthority(Authority groupName) {
        authority = groupName.name();
    }

    public UserAuthority(String groupName) {
        authority = groupName;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
