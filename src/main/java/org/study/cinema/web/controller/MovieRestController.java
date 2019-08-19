package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.services.MovieService;
import org.study.cinema.utils.StringParser;

@RestController
public class MovieRestController {

    private static final Logger LOGGER = LogManager.getLogger(MovieRestController.class);

    @Autowired
    private MovieService movieService;

    //TODO check work
    @RequestMapping(method = RequestMethod.POST,
            headers = "Accept=application/x-www-form-urlencoded")
    @ResponseBody
    public void getAddNewMovie(MovieDto movieDto) {

        if (wrongInputParameters(movieDto)) {
            LOGGER.info("Movie parameters is incorrect");
        }
        LOGGER.info("Movie" + movieDto.toString());
        movieService.addNewMovie(movieDto);
        LOGGER.info("Movie with define parameters was added");
    }

    private boolean wrongInputParameters(MovieDto movieDto) {
        boolean name = StringParser.checkMovieNameDescription(movieDto.getMovieName());
        boolean description = StringParser.checkMovieNameDescription(movieDto.getMovieDescription());
        boolean duration = StringParser.checkMovieDuration(String.valueOf(movieDto.getMovieDuration()));
        boolean age = StringParser.checkMovieAge(String.valueOf(movieDto.getAgeLimit()));

        return name && description && duration && age;
    }
}
