package org.study.cinema.services.impl;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.SecurityUser;
import org.study.cinema.repositories.UserRepository;
import org.study.cinema.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {

        RegisteredUser registeredUser = userRepository
                .findByUserLogin(userLogin);

        if (Objects.isNull(registeredUser)) {
            throw new UsernameNotFoundException(userLogin);
        }

        return new SecurityUser(registeredUser);
    }
}
