package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.exceptions.IncorrectInputData;
import org.study.cinema.services.MovieService;
import org.study.cinema.utils.StringParser;

@RestController
public class MovieRestController {

    private static final Logger LOGGER = LogManager.getLogger(MovieRestController.class);

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/admin/addmovie", method = RequestMethod.POST,
            headers = "Accept=application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public void getAddNewMovie(@RequestBody MovieDto movieDto) {
        if (wrongInputParameters(movieDto)) {
            LOGGER.info("Data for new movie is incorrect");
            throw new IncorrectInputData("Incorrect input parameters");
        }
        LOGGER.info("New movie is " + movieDto.toString());
        movieService.addNewMovie(movieDto);
        LOGGER.info("Movie with define parameters was added");
    }

    private boolean wrongInputParameters(MovieDto movieDto) {
        boolean name = StringParser.checkMovieNameDescription(movieDto.getMovieName());
        boolean description = StringParser.checkMovieNameDescription(movieDto.getMovieDescription());
        boolean duration = StringParser.checkMovieDuration(String.valueOf(movieDto.getMovieDuration()));
        boolean age = StringParser.checkMovieAge(String.valueOf(movieDto.getAgeLimit()));

        return !(name && description && duration && age);
    }
}
