package com.toncent.security.service;

import com.toncent.security.domain.Role;
import com.toncent.security.domain.User;
import com.toncent.security.repository.RoleRepository;
import com.toncent.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/4
 * TIME  : 11:29
 */
@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public Iterable<Role> listRoles() {
        Iterable<Role> roles = roleRepository.findAll();
        return roles;
    }

}
