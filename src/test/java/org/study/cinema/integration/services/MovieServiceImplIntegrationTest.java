package org.study.cinema.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.Schedule;
import org.study.cinema.repositories.MovieRepository;
import org.study.cinema.services.impl.MovieServiceImpl;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class MovieServiceImplIntegrationTest {

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void shouldGetActiveMovieList() {
        List<MovieDto> resultActiveMovieList = movieService.viewAllAvailableMovies();

        assertThat("Active MovieList size should be 8", resultActiveMovieList, hasSize(8));
    }

    @Test
    public void shouldGetNoActiveMovieList() {
        List<MovieDto> resultActiveMovieList = movieService.viewAllUnAvailableMovies();

        assertThat("Non Active MovieList size should be 1", resultActiveMovieList, hasSize(1));
    }

    @Test
    public void shouldAddNewMovie() {
        MovieDto newMovieDto = createTestMovie();

        movieService.addNewMovie(newMovieDto);
        Movie resultMovie = movieRepository.getOne(11);

        assertThat(resultMovie.getMovieName(), equalTo(newMovieDto.getMovieName()));
        assertThat(resultMovie.getGenre().getGenre(), equalTo(newMovieDto.getMovieGenre()));
        assertThat(resultMovie.getAgeLimit(), equalTo(newMovieDto.getAgeLimit()));
        assertThat(resultMovie.getMovieDuration(), equalTo(newMovieDto.getMovieDuration()));
        assertThat(resultMovie.getMovieDescription(), equalTo(newMovieDto.getMovieDescription()));
    }

    @Test
    public void shouldCancelMovieInTheCinema() {
        int movieId = 4;
        MovieDto resultMovieDto = movieService.cancelMovieById(movieId);
        List<Schedule> resultCancelledScheduleList = resultMovieDto.getScheduleList();
        Set<RegisteredUser> resultRegisteredUsersList = resultMovieDto.getRegisteredUsers();

        assertThat("Non Active ScheduleLIst size should be 9", resultCancelledScheduleList, hasSize(9));
        assertThat(resultCancelledScheduleList.get(0).isActive(), equalTo(false));
        assertThat("notification send for 1 user", resultRegisteredUsersList, hasSize(1));

        Movie resultMovie = movieRepository.getOne(movieId);
        assertThat(resultMovie.isActive(), equalTo(false));
    }

    private MovieDto createTestMovie() {
        return MovieDto.builder()
                .movieId(10)
                .movieName("Titanic")
                .movieGenre("melodrama")
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build();
    }
}