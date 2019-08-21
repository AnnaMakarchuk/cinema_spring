package org.study.cinema.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;
import org.study.cinema.repositories.GenreRepository;
import org.study.cinema.repositories.MovieRepository;
import org.study.cinema.utils.MovieDtoConverter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    private Optional<Movie> firstMovie;
    private Optional<Movie> secondMovie;

    private MovieDto firstMovieDto;
    private MovieDto secondMovieDto;

    @Before
    public void setUp() {
        Genre genre = Genre.builder()
                .id(0)
                .genre("action")
                .build();

        firstMovie = Optional.of(
                Movie.builder()
                        .id(0)
                        .movieName("Avengers")
                        .genre(genre)
                        .movieDuration(100)
                        .ageLimit(16)
                        .movieDescription("no")
                        .build());
        secondMovie = Optional.of(
                Movie.builder()
                        .id(1)
                        .movieName("Dark")
                        .genre(genre)
                        .movieDuration(100)
                        .ageLimit(16)
                        .movieDescription("no")
                        .build());
        firstMovieDto = MovieDto.builder()
                .movieId(0)
                .movieName("Avengers")
                .movieGenre("ACTION")
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build();
        secondMovieDto = MovieDto.builder()
                .movieId(1)
                .movieName("Dark")
                .movieGenre("ACTION")
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build();
    }

    @Test
    public void shouldCallGetAllMethodMovieDAO() {
        movieServiceImpl.viewAllAvailableMovies();
        verify(movieRepository).findByIsActive(true);
    }

    @Test
    public void shouldReturnAllAvailableMovies() {
        List<MovieDto> expectedMoviesDtoList = Arrays.asList(firstMovieDto, secondMovieDto);

        when(movieRepository.findByIsActive(true)).thenReturn(Arrays.asList(firstMovie.get(), secondMovie.get()));
        List<MovieDto> resultMoviesDtoList = movieServiceImpl.viewAllAvailableMovies();

        assertThat(resultMoviesDtoList, equalTo(expectedMoviesDtoList));
    }

    @Test
    public void shouldReturnNullIfMoviesNotAvailable() {
        when(movieRepository.findByIsActive(true)).thenReturn(Collections.emptyList());
        List<MovieDto> resultMoviesDtoList = movieServiceImpl.viewAllAvailableMovies();

        assertThat(resultMoviesDtoList, nullValue());
    }

    @Test
    public void shouldCallCreateMethodMovieDAO() {
        movieServiceImpl.addNewMovie(firstMovieDto);

        Movie movie = Optional.ofNullable(firstMovie.get()).orElse(null);

        verify(genreRepository).findByGenre("action");
        verify(movieRepository).save(movie);
    }
}
