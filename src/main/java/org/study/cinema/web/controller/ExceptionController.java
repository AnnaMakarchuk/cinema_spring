package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.study.cinema.exceptions.DataNotFound;
import org.study.cinema.exceptions.IncorrectInputData;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
    private static final Logger LOGGER = LogManager.getLogger(ExceptionController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String viewNotFoundPage() {
        return "errorPages/404";
    }

    @ExceptionHandler(value = IncorrectInputData.class)
    public ModelAndView viewIncorrectInputDataPage(HttpServletRequest req, Exception ex) {
        LOGGER.error("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.setStatus(HttpStatus.BAD_REQUEST);
        mav.setViewName("errorPages/400");
        return mav;
    }

    @ExceptionHandler(value = DataNotFound.class)
    public ModelAndView viewPageForDataNotFoundInDatabase(HttpServletRequest req, Exception ex) {
        LOGGER.error("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.setStatus(HttpStatus.BAD_REQUEST);
        mav.setViewName("errorPages/400");
        return mav;
    }
}
