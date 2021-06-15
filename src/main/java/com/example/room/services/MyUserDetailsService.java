package com.example.room.services;

import com.example.room.models.entities.Employee;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class MyUserDetailsService implements UserDetailsService {
    private final AccountServices accountServices;
    private final RoleService roleService;


    public MyUserDetailsService(AccountServices accountServices, RoleService roleService) {
        this.accountServices = accountServices;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = accountServices.findAccount(email);
        if (employee == null){
            throw new BadCredentialsException("Username not found");
        }
        Set<GrantedAuthority> grantedAuthority =  new HashSet<>();
        List<String> roleName = roleService.findRole(employee.getId());
        for(String rolename : roleName){
            grantedAuthority.add(new SimpleGrantedAuthority(rolename));
        }

        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), grantedAuthority);

    }
}
