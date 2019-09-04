package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.services.MovieService;
import org.study.cinema.utils.AttributesNames;

import java.util.List;

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
        return "adminPages/admin_change_movie";
    }

    @GetMapping("/admin/unactivemovies")
    public String getUnActiveMovies(Model model) {
        List<MovieDto> unActiveMovieDtoList = movieService.viewAllUnAvailableMovies();
        LOGGER.info("List of unActive MovieDTO for main page was obtained");
        model.addAttribute(AttributesNames.MOVIES, unActiveMovieDtoList);
        return "adminPages/admin_nonactive_movies";
    }

    @RequestMapping(value = "/admin/cancelmovie", method = RequestMethod.POST)
    public String cancelMovie(@RequestParam(name = AttributesNames.MOVIE_ID) int movieId,
                              Model model) {
        MovieDto cancelMovie = movieService.cancelMovieById(movieId);
        LOGGER.info("Cancelled Movie Dto was created added");

        model.addAttribute(AttributesNames.SCHEDULES, cancelMovie.getScheduleList());
        model.addAttribute(AttributesNames.CLIENTS, cancelMovie.getRegisteredUsers());
        return "adminPages/admin_cancel_movie";
    }

    private void createMovieDtoListForView(Model model) {
        List<MovieDto> movieDtoList = movieService.viewAllAvailableMovies();
        LOGGER.info("List MovieDTO for main page was obtained" + movieDtoList.toString());
        model.addAttribute(AttributesNames.MOVIES, movieDtoList);
    }
}
