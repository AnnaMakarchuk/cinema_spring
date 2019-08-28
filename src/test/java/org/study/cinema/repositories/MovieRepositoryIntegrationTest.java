package org.study.cinema.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.CinemaApplicationTests;
import org.study.cinema.entity.Movie;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@Transactional
public class MovieRepositoryIntegrationTest extends CinemaApplicationTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void shouldSelectActiveMovieList() {
        List<String> expectedMovieName = Arrays
                .asList("Avengers11: EndGame", "Alladin", "KuToppen", "Dark Phoenix", "A Star Is Born", "Made In Italy",
                        "Shazam!", "The Secret Life of Pets 2");

        boolean isActive = true;
        List<Movie> resultActiveMovieList = movieRepository.findByIsActive(isActive);
        List<String> resultMovieName = resultActiveMovieList.stream()
                .map(Movie::getMovieName)
                .collect(Collectors.toList());

        assertThat("Active MovieList size should be 8", resultActiveMovieList, hasSize(8));
        assertThat(resultMovieName, equalTo(expectedMovieName));
    }

    @Test
    public void shouldUpdateMovie() {
        int movieIdUnActive = 4;

        movieRepository.updateByMovieId(4);
        Movie resultMovie = movieRepository.getOne(movieIdUnActive);

        assertThat(resultMovie.isActive(), equalTo(false));
    }
}
