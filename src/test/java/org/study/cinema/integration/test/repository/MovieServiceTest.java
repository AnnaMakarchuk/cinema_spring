package org.study.cinema.integration.test.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.ui.Model;
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
import java.util.Optional;

@AutoConfigureMockMvc
public class MovieServiceTest extends CinemaApplicationTests {

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
        Genre expectedGenre = Genre.builder()
                .id(3)
                .genre("comedy")
                .build();


    }

    private void saveGenreToDatabase() {
        List<Genre> genres = genreList();
        genres.forEach(g -> genreRepository.save(g));
    }

    private void saveMovieToDatabase() {
        List<Movie> moviesForDatabase = movieList();
        moviesForDatabase.forEach(m -> movieRepository.save(m));
    }


    private List<Genre> genreList() {
        List<Genre> genreForDatabase = new ArrayList<>();
        genreForDatabase.add(Genre.builder()
                .id(1)
                .genre("action")
                .build());
        genreForDatabase.add(Genre.builder()
                .id(2)
                .genre("cartoon")
                .build());
        genreForDatabase.add(Genre.builder()
                .id(3)
                .genre("comedy")
                .build());
        genreForDatabase.add(Genre.builder()
                .id(4)
                .genre("thriller")
                .build());
        return genreForDatabase;
    }

    private List<Movie> movieList() {
        List<Movie> moviesForDatabase = new ArrayList<>();
        moviesForDatabase.add(Movie.builder()
                .id(1)
                .movieName("Avengers")
                .genre(genreList().get(0))
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        moviesForDatabase.add(Movie.builder()
                .id(2)
                .movieName("Dark")
                .genre(genreList().get(0))
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        moviesForDatabase.add(Movie.builder()
                .id(3)
                .movieName("Shazam!")
                .genre(genreList().get(2))
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        return moviesForDatabase;
    }

    private List<Schedule> scheduleList() {
        List<Movie> movies = movieList();
        List<Schedule> schedulesForDatabase = new ArrayList<>();

        return schedulesForDatabase;

    }
}