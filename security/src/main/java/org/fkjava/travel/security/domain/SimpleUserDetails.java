package org.fkjava.travel.security.domain;

import java.util.Collection;

import org.fkjava.travel.identity.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SimpleUserDetails implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    public SimpleUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isPasswordNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
