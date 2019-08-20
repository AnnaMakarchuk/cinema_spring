package org.study.cinema.utils;

import org.junit.Before;
import org.junit.Test;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MovieDtoConverterTest {

    private Optional<Movie> firstMovie;
    private Optional<Movie> secondMovie;
    private MovieDto firstMovieDto;
    private MovieDto secondMovieDto;
    private Genre genre;

    @Before
    public void setUp() {
        genre = Genre.builder()
                .id(1)
                .genre("action")
                .build();
        firstMovie = Optional.of(
                Movie.builder()
                        .movieName("Avengers")
                        .genre(genre)
                        .movieDuration(100)
                        .ageLimit(16)
                        .movieDescription("no")
                        .build());
        secondMovie = Optional.of(
                Movie.builder()
                        .movieName("Dark")
                        .genre(genre)
                        .movieDuration(100)
                        .ageLimit(16)
                        .movieDescription("no")
                        .build());
        firstMovieDto = MovieDto.builder()
                .movieName("Avengers")
                .movieGenre("ACTION")
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build();
        secondMovieDto = MovieDto.builder()
                .movieName("Dark")
                .movieGenre("ACTION")
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build();
    }

    @Test
    public void shouldConvertMovieListDto() {
        List<MovieDto> expectedMoviesDtoList = Arrays.asList(firstMovieDto, secondMovieDto);

        List<MovieDto> resultMoviesDtoList = MovieDtoConverter
                .convertMovieListInMovieDtoList(Arrays.asList(firstMovie.get(), secondMovie.get()));

        assertThat(resultMoviesDtoList, equalTo(expectedMoviesDtoList));
    }

    @Test
    public void shouldConvertMovie() {
        Movie expectedMovie = Optional.ofNullable(firstMovie.get()).orElse(null);

        Movie resultMovie = MovieDtoConverter.convertMovieDtoInMovie(firstMovieDto, genre);

        assertThat(resultMovie, equalTo(expectedMovie));
    }
}
