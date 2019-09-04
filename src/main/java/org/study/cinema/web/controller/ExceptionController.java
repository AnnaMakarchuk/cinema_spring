package org.study.cinema.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.study.cinema.exceptions.DataNotFound;
import org.study.cinema.exceptions.IncorrectInputData;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String viewNotFoundPage() {
        return "errorPages/404";
    }

    @ExceptionHandler(value = IncorrectInputData.class)
    public String viewIncorrectInputDataPage() {
        return "errorPages/401";
    }

    @ExceptionHandler(value = DataNotFound.class)
    public String viewPageForDataNotFoundInDatabase() {
        return "errorPages/404";
    }
}
