package org.study.cinema.integration.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.study.cinema.repositories.GenreRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class GenreRepositoryIntegrationTest {

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
