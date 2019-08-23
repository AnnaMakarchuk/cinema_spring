package org.study.cinema.services.impl;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void shouldCallGetAllMethodMovieDAO() {
        movieServiceImpl.viewAllAvailableMovies();
        verify(movieRepository).findByIsActive(true);
    }

    @Test
    public void shouldReturnAllAvailableMovies() {
        List<MovieDto> expectedMoviesDtoList = createTestMovieDtoList();

        when(movieRepository.findByIsActive(true)).thenReturn(createTestMovieList());
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
        movieServiceImpl.addNewMovie(createTestMovieDtoList().get(0));

        Movie movie = createTestMovieList().get(0);
        movie.setActive(true);

        verify(genreRepository).findByGenre("action");
        verify(movieRepository).save(movie);
    }


    private List<Genre> createTestGenreList() {
        List<Genre> genreForDatabase = new ArrayList<>();
        genreForDatabase.add(Genre.builder()
                .id(0)
                .genre("action")
                .build());
        genreForDatabase.add(Genre.builder()
                .id(1)
                .genre("cartoon")
                .build());
        genreForDatabase.add(Genre.builder()
                .id(2)
                .genre("comedy")
                .build());
        genreForDatabase.add(Genre.builder()
                .id(3)
                .genre("thriller")
                .build());
        return genreForDatabase;
    }

    private List<Movie> createTestMovieList() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(Movie.builder()
                .id(0)
                .movieName("Avengers")
                .genre(createTestGenreList().get(0))
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        movieList.add(Movie.builder()
                .id(1)
                .movieName("Dark")
                .genre(createTestGenreList().get(0))
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        movieList.add(Movie.builder()
                .id(2)
                .movieName("Shazam!")
                .genre(createTestGenreList().get(2))
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        return movieList;
    }

    private List<MovieDto> createTestMovieDtoList() {
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(MovieDto.builder()
                .movieId(0)
                .movieName("Avengers")
                .movieGenre("action")
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        movieDtoList.add(MovieDto.builder()
                .movieId(1)
                .movieName("Dark")
                .movieGenre("action")
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        movieDtoList.add(MovieDto.builder()
                .movieId(2)
                .movieName("Shazam!")
                .movieGenre("comedy")
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        return movieDtoList;
    }
}
