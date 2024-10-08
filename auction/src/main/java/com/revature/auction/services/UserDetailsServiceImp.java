package com.revature.auction.services;

import com.revature.auction.models.User;
import com.revature.auction.models.CustomUserDetails;
import com.revature.auction.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImp implements UserDetailsService
{
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepo.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("Could not find user!!!");
        }

        return new CustomUserDetails(user);
    }
}
