package com.campbackend.configuration.securityconfiguration;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.campbackend.modal.AccountHolder;

public class UserDetailPrinciple implements UserDetails {
    private AccountHolder accountHolder;

    public UserDetailPrinciple(AccountHolder data) {
        this.accountHolder = data;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(String.valueOf(accountHolder.getRole()));
        return Collections.singleton(auth);
    }

    @Override
    public String getPassword() {
        return accountHolder.getPassword();
    }

    @Override
    public String getUsername() {
        return accountHolder.getEmail();
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