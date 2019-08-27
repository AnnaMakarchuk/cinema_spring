package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.enums.Gender;
import org.study.cinema.services.UserService;
import org.study.cinema.utils.StringParser;

@RestController
public class RegisteredUserRestController {

    private static final Logger LOGGER = LogManager.getLogger(RegisteredUserRestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/cabinet/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updateUserInformation(RegisteredUserDto registeredUserDto) throws Exception {
        if (wrongInputParameters(registeredUserDto)) {
            LOGGER.info("User parameters is incorrect");
            throw new Exception("Incorrect input parameters");
        }
        userService.updateUser(registeredUserDto);
        LOGGER.info("User with define parameters was updated");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createNewUser(String userName, String userSurname, String gender, String userLogin,
                              String userEMailAddress, String userPassword) {
        if (wrongInputParametersForNewUser(userName, userSurname, userLogin,
                userEMailAddress, userPassword)) {
            LOGGER.info("User parameters is incorrect");
            try {
                throw new Exception("Incorrect input parameters");
            } catch (Exception e) {
                LOGGER.error("Incorrect input parameters", e);
            }
        }
        RegisteredUserDto newRegisteredUser = RegisteredUserDto.builder()
                .userName(userName)
                .userSurname(userSurname)
                .gender(Gender.valueOf(gender.toUpperCase()))
                .userLogin(userLogin)
                .userEMailAddress(userEMailAddress)
                .userPassword(userPassword)
                .build();
        try {
            userService.createNewUser(newRegisteredUser);
        } catch (Exception e) {
            LOGGER.error("UserRole not found in database", e);
        }
        LOGGER.info("User with define parameters was created" + newRegisteredUser);
    }

    private boolean wrongInputParameters(RegisteredUserDto registeredUserDto) {
        boolean login = StringParser.checkLoginPassword(registeredUserDto.getUserLogin());
        boolean password = StringParser.checkLoginPassword(registeredUserDto.getUserPassword());

        return !(login && password);
    }

    private boolean wrongInputParametersForNewUser(String userName, String userSurname, String userLogin,
                                                   String userEMailAddress, String userPassword) {
        boolean name = StringParser.checkNameSurname(userName);
        boolean surname = StringParser.checkNameSurname(userSurname);
        boolean login = StringParser.checkLoginPassword(userLogin);
        boolean password = StringParser.checkLoginPassword(userPassword);
        boolean email = StringParser.checkEMail(userEMailAddress);

        return !(name && surname && login && password && email);
    }
}
