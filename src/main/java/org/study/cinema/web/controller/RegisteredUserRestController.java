package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.exceptions.DataNotFound;
import org.study.cinema.exceptions.IncorrectInputData;
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
            LOGGER.info("Data for user updating is incorrect");
            throw new IncorrectInputData("Incorrect input parameters");
        }
        userService.updateUser(registeredUserDto);
        LOGGER.info("User with define parameters was updated");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            headers = "Accept=application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public void createNewUser(RegisteredUserDto registeredUserDto) {
        if (wrongInputParametersForNewUser(registeredUserDto)) {
            LOGGER.info("Data for new user is incorrect");
            throw new IncorrectInputData("Incorrect input parameters");
        }
        LOGGER.info("User with define parameters was created" + registeredUserDto);
        try {
            userService.createNewUser(registeredUserDto);
        } catch (Exception e) {
            LOGGER.error("UserRole not found in database", e);
            throw new DataNotFound("Data not found in database");
        }
        LOGGER.info("User with define parameters was created" + registeredUserDto);
    }

    private boolean wrongInputParameters(RegisteredUserDto registeredUserDto) {
        boolean login = StringParser.checkLoginPassword(registeredUserDto.getUserLogin());
        boolean password = StringParser.checkLoginPassword(registeredUserDto.getUserPassword());

        return !(login && password);
    }

    private boolean wrongInputParametersForNewUser(RegisteredUserDto registeredUserDto) {
        boolean name = StringParser.checkNameSurname(registeredUserDto.getUserName());
        boolean surname = StringParser.checkNameSurname(registeredUserDto.getUserSurname());
        boolean login = StringParser.checkLoginPassword(registeredUserDto.getUserLogin());
        boolean password = StringParser.checkLoginPassword(registeredUserDto.getUserPassword());
        boolean email = StringParser.checkEMail(registeredUserDto.getUserEMailAddress());

        return !(name && surname && login && password && email);
    }
}
