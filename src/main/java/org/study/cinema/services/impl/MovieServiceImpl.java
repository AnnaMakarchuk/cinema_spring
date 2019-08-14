package org.study.cinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;
import org.study.cinema.repositories.GenreRepository;
import org.study.cinema.repositories.MovieRepository;
import org.study.cinema.services.MovieService;
import org.study.cinema.utils.MovieDtoConverter;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LogManager.getLogger(MovieServiceImpl.class);
    private boolean isActive = true;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<MovieDto> viewAllAvailableMovies() {
        List<Optional<Movie>> activeMovies = movieRepository.findByIsActive(isActive);
        if (activeMovies.isEmpty()) {
            return null;
        }
        LOGGER.info("MovieService return list of active movies from database");
        return MovieDtoConverter.convertMovieListInMovieDtoList(activeMovies);
    }

    @Override
    public void addNewMovie(MovieDto movieDto) {
        Genre genre = generateGenre(movieDto);
        Movie movie = MovieDtoConverter.convertMovieDtoInMovie(movieDto, genre);
        LOGGER.info("Movie is prepared fro save in database");
        movieRepository.save(movie);
    }

    private Genre generateGenre(MovieDto movieDto) {
        String genreName = movieDto.getMovieGenre().toLowerCase();
        int genreId = genreRepository.findByGenre(genreName);
        return Genre.builder().
                id(genreId)
                .genre(genreName)
                .build();
    }
}
