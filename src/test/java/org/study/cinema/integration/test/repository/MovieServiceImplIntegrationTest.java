package org.study.cinema.integration.test.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.study.cinema.CinemaApplicationTests;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.Schedule;
import org.study.cinema.repositories.GenreRepository;
import org.study.cinema.repositories.MovieRepository;
import org.study.cinema.repositories.ScheduleRepository;
import org.study.cinema.repositories.TicketRepository;
import org.study.cinema.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
public class MovieServiceImplIntegrationTest extends CinemaApplicationTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void shouldReturnGenreIdByNameFromDatabase() {





    }

}