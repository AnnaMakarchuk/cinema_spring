package org.study.cinema.integration.test.repository;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.study.cinema.CinemaApplicationTests;
import org.study.cinema.entity.Movie;
import org.study.cinema.repositories.MovieRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class MovieRepositoryIntegrationTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void shouldReturnActiveMoviesFromDatabase() {
        List<Movie> movies = movieRepository.findByIsActive(true);
        assertThat(movies, hasSize(0));
    }

}
