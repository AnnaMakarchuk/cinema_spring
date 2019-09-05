package org.study.cinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.UserRole;
import org.study.cinema.repositories.UserRepository;
import org.study.cinema.repositories.UserRoleRepository;
import org.study.cinema.services.UserService;
import org.study.cinema.utils.UserDtoConverter;

//TODO add spring Security in the end
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private static final String NEW_USER_ROLE = "client";

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void updateUser(RegisteredUserDto registeredUserDto) {
        userRepository.updateUser(
                registeredUserDto.getUserLogin(), registeredUserDto.getUserPassword(), registeredUserDto.getUserId());
        LOGGER.info("User was updated in database");
    }

    @Override
    public void createNewUser(RegisteredUserDto registeredUserDto) throws Exception {
        RegisteredUser newRegisteredUser = UserDtoConverter.convertUserDtoInRegisteredUser(registeredUserDto);
        LOGGER.info("User was prepared for saving in database");
        UserRole userRole = userRoleRepository.findByUserRole(NEW_USER_ROLE)
                .orElseThrow(() -> new Exception("UserRole not found in database"));
        newRegisteredUser.setUserRole(userRole);
        userRepository.save(newRegisteredUser);
        LOGGER.info("User was saved in database");
    }

    //    @Override
//    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
//
//        RegisteredUser registeredUser = userRepository
//                .findByUserLogin(userLogin);
//
//        if (Objects.isNull(registeredUser)) {
//            throw new UsernameNotFoundException(userLogin);
//        }
//
//        return new SecurityUser(registeredUser);
//    }
}
