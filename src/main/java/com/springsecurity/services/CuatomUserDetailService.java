package com.springsecurity.services;

import com.springsecurity.Repo.UserRepository;
import com.springsecurity.models.CustomUserDetail;
import com.springsecurity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CuatomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= this.userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("No User");
        }
        return new CustomUserDetail(user);
    }
}
