package org.study.cinema.services;

import org.study.cinema.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> viewAllAvailableMovies();

    void addNewMovie(MovieDto movieDto);

    List<MovieDto> viewAllUnAvailableMovies();

    MovieDto cancelMovieById(int movieId);
}
