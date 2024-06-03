package com.campbackend.configuration.securityconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.campbackend.modal.AccountHolder;
import com.campbackend.services.AccountHolderServices;
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired private AccountHolderServices accountHolderServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountHolder accountHolder = accountHolderServices.findByEmail(username);
        if (accountHolder == null)  
            throw new UsernameNotFoundException("Unimplemented method  loadUserByUsername");
        return new UserDetailPrinciple(accountHolder);
    }
}