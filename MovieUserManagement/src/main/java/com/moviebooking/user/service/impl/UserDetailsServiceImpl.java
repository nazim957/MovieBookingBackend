package com.moviebooking.user.service.impl;

import com.moviebooking.user.model.User;
import com.moviebooking.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
        if(user==null)
        {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("No user found!!");
        }
        return user;
    }
}
