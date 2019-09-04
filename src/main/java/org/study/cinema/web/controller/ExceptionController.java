package org.study.cinema.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.study.cinema.exceptions.IncorrectInputData;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = IncorrectInputData.class)
    public String viewIncorrectInputDataPage() {
        return "errorPages/401";
    }
}
