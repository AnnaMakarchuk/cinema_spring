package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.services.MovieService;
import org.study.cinema.utils.AttributesNames;
import org.study.cinema.utils.StringParser;

import java.util.List;
import java.util.Objects;

@Controller
public class MovieController {

    private static final Logger LOGGER = LogManager.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String getMainPageWithMovies(Model model) {
        createMovieDtoListForView(model);
        return "homepage";
    }


    @GetMapping("/admin/movies")
    public String getMovieListForAdmin(Model model) {
        createMovieDtoListForView(model);
        return "adminPages/admin_add_movie";
    }

    //TODO Check problem with forbidden
    @PostMapping("/admin/addmovie")
    public String getMovieListAddNewMovie(@RequestParam(name = AttributesNames.MOVIE_NAME) String movieName,
                                          @RequestParam(name = AttributesNames.MOVIE_GENRE) String genre,
                                          @RequestParam(name = AttributesNames.DURATION) String duration,
                                          @RequestParam(name = AttributesNames.AGE) String ageLimit,
                                          @RequestParam(name = AttributesNames.DESCRIPTION) String movieDescription,
                                          Model model) {
        if (isNulls(movieName, movieDescription)) {
            return "clientPages/client_account_update";
        }

        if (wrongInputParameters(movieName, movieDescription, Integer.parseInt(duration), Integer.parseInt(duration))) {
            LOGGER.info("Movie parameters is incorrect");
            return "pages/401";
        }

        LOGGER.info("Movie parameters is correct");
        MovieDto movieDto = MovieDto.builder()
                .movieName(movieName)
                .movieGenre(genre)
                .movieDuration(Integer.parseInt(duration))
                .ageLimit(Integer.parseInt(ageLimit))
                .movieDescription(movieDescription)
                .build();

        movieService.addNewMovie(movieDto);
        LOGGER.info("Movie with define parameters was added");
        return "adminPages/admin_movieadded";
    }

    private void createMovieDtoListForView(Model model) {
        List<MovieDto> movieDtoList = movieService.viewAllAvailableMovies();
        LOGGER.info("List MovieDTO for main page was obtained" + movieDtoList.toString());
        model.addAttribute(AttributesNames.MOVIES, movieDtoList);
    }

    private boolean isNulls(String movieName, String movieDescription) {
        return Objects.isNull(movieName) || Objects.isNull(movieDescription);
    }

    private boolean wrongInputParameters(String movieName, String movieDescription, int movieDuration, int ageLimit) {
        boolean name = StringParser.checkMovieNameDescription(movieName);
        boolean description = StringParser.checkMovieNameDescription(movieDescription);
        boolean duration = StringParser.checkMovieDuration(String.valueOf(movieDuration));
        boolean age = StringParser.checkMovieAge(String.valueOf(ageLimit));

        return name && description && duration && age;
    }
}
