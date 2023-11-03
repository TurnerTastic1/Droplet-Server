//package com.TCorp.FitNetServer.api.security;
//
//import com.TCorp.FitNetServer.api.model.Role;
//import com.TCorp.FitNetServer.api.model.UserEntity;
//import com.TCorp.FitNetServer.api.repository.UserEntityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
///**
// * File: CustomUserDetailsService
// * Author: turnernaef
// * Date: 10/31/23
// * Description:
// */
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserEntityRepository userEntityRepository;
//
//    @Autowired
//    public CustomUserDetailsService(UserEntityRepository userEntityRepository) {
//        this.userEntityRepository = userEntityRepository;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userEntityRepository.findUserEntityByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }
//
//    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//    }
//}
