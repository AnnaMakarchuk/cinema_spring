package org.study.cinema.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.study.cinema.dto.RegisteredUserDto;

@RestController
@RequestMapping(value = "/")
public class LoginFormController {


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void loginFormController(RegisteredUserDto registeredUserDto){
    }


}
