package org.study.cinema.unit.utils;

import org.junit.Test;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;
import org.study.cinema.utils.MovieDtoConverter;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MovieDtoConverterTest {

    @Test
    public void shouldConvertMovieListDto() {
        List<MovieDto> expectedMoviesDtoList = createTestMovieDtoList();

        List<MovieDto> resultMoviesDtoList = MovieDtoConverter
                .convertMovieListInMovieDtoList(createTestMovieList());

        assertThat(resultMoviesDtoList, equalTo(expectedMoviesDtoList));
    }

    @Test
    public void shouldConvertMovie() {
        Movie expectedMovie = createTestMovieList().get(0);
        expectedMovie.setActive(true);

        Movie resultMovie = MovieDtoConverter.convertMovieDtoInMovie(createTestMovieDtoList().get(0), createTestGenreList().get(0));

        assertThat(resultMovie, equalTo(expectedMovie));
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
