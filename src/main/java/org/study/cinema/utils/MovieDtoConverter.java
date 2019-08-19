package org.study.cinema.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MovieDtoConverter {

    private static final Logger LOGGER = LogManager.getLogger(MovieDtoConverter.class);

    public static List<MovieDto> convertMovieListInMovieDtoList(List<Optional<Movie>> movieList) {
        List<MovieDto> movieDtos = movieList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(MovieDtoConverter::movieConverter)
                .collect(Collectors.toList());
        LOGGER.info("Movie list was converted in MovieDto list ");
        return movieDtos;
    }

    private static MovieDto movieConverter(Movie movie) {
        return MovieDto.builder()
                .movieId(movie.getId())
                .movieName(movie.getMovieName())
                .movieGenre(movie.getGenre().getGenre().toUpperCase())
                .movieDuration(movie.getMovieDuration())
                .ageLimit(movie.getAgeLimit())
                .movieDescription(movie.getMovieDescription())
                .build();
    }

    public static Movie convertMovieDtoInMovie(MovieDto movieDto, Genre genre) {
        return Movie.builder()
                .movieName(movieDto.getMovieName())
                .genre(genre)
                .movieDuration(movieDto.getMovieDuration())
                .ageLimit(movieDto.getAgeLimit())
                .movieDescription(movieDto.getMovieDescription())
                .build();
    }
}
