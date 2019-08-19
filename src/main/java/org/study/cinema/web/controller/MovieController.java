package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.services.MovieService;
import org.study.cinema.utils.AttributesNames;
import org.study.cinema.utils.StringParser;

import javax.validation.Valid;
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

    @GetMapping("/admin/unactivemovies")
    public String getUnActiveMovies(Model model) {
        List<MovieDto> unActiveMovieDtoList = movieService.viewAllUnAvailableMovies();
        LOGGER.info("List of unActive MovieDTO for main page was obtained");
        model.addAttribute(AttributesNames.MOVIES, unActiveMovieDtoList);
        return "adminPages/admin_nonactive_movies";
    }

    //TODO problem with attribute model, change to Rest
//    @RequestMapping(value = "/admin/addmovie", method = RequestMethod.POST)
    public String getMovieListAddNewMovie(@ModelAttribute (name = AttributesNames.MOVIE) MovieDto movieDto) {

        if (wrongInputParameters(movieDto)) {
            LOGGER.info("Movie parameters is incorrect");
            return "401";
        }
        LOGGER.info("Movie" + movieDto.toString());
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

    private boolean wrongInputParameters(MovieDto movieDto) {
        boolean name = StringParser.checkMovieNameDescription(movieDto.getMovieName());
        boolean description = StringParser.checkMovieNameDescription(movieDto.getMovieDescription());
        boolean duration = StringParser.checkMovieDuration(String.valueOf(movieDto.getMovieDuration()));
        boolean age = StringParser.checkMovieAge(String.valueOf(movieDto.getAgeLimit()));

        return name && description && duration && age;
    }
}
