package com.example.Configs;

import com.example.DTOs.UsersDTO;
import com.example.Servicee.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UsersDTO usersDTO = userService.findByUserName(s);
        if (usersDTO == null ){

            throw new UsernameNotFoundException(s);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usersDTO.getRole().getRolename()));
    UserDetails userDetails = new org.springframework.security.core.userdetails
            .User(usersDTO.getUsername(),usersDTO.getPassword(),authorities);
    return userDetails;
    }
}
