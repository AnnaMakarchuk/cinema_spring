package org.study.cinema.services;

//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.study.cinema.dto.RegisteredUserDto;

//TODO add spring Security in the end
public interface UserService
//        extends UserDetailsService
{

//    UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException;

    void updateUser(RegisteredUserDto registeredUserDto);

    void createNewUser(RegisteredUserDto newRegisteredUser) throws Exception;
}
