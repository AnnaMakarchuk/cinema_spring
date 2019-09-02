package org.study.cinema.integration.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.cinema.CinemaApplicationTests;
import org.study.cinema.repositories.GenreRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GenreRepositoryIntegrationTest extends CinemaApplicationTests {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void shouldReturnGenreIdByGenreNameAction() {
        int resultGenreId = genreRepository.findByGenre("action");

        assertThat("Id for genre action should be 3", resultGenreId, equalTo(3));
    }

    @Test
    public void shouldReturnGenreIdByGenreNameComedy() {
        int resultGenreId = genreRepository.findByGenre("comedy");

        assertThat("Id for genre comedy should be 1", resultGenreId, equalTo(1));
    }
}
