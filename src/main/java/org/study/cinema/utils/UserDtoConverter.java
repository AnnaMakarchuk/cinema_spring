package org.study.cinema.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.RegisteredUser;

public class UserDtoConverter {
    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    public static RegisteredUserDto convertUserInRegisteredUserDto(RegisteredUser registeredUser) {
        RegisteredUserDto registeredUserDto = RegisteredUserDto.builder()
                .userId(registeredUser.getUserId())
                .userName(registeredUser.getUserName())
                .userSurname(registeredUser.getUserSurname())
                .gender(registeredUser.getGender())
                .userRole(registeredUser.getUserRole())
                .userLogin(registeredUser.getUserLogin())
                .userEMailAddress(registeredUser.getUserEMailAddress())
                .build();
        LOGGER.info("RegisteredUserDto was converted " + registeredUserDto.toString());
        return registeredUserDto;
    }

    public static RegisteredUser convertUserDtoInRegisteredUser(RegisteredUserDto registeredUserDto) {
        RegisteredUser registeredUser = new RegisteredUser(registeredUserDto.getUserName(),
                registeredUserDto.getUserSurname(),
                registeredUserDto.getGender(),
                registeredUserDto.getUserLogin(),
                registeredUserDto.getUserEMailAddress(),
                registeredUserDto.getUserPassword());
        LOGGER.info("RegisteredUser was converted " + registeredUser.toString());
        return registeredUser;
    }
}
