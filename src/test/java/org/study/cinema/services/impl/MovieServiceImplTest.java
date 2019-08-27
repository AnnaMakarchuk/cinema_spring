package org.study.cinema.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.Ticket;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;
import org.study.cinema.entity.enums.WeekDay;
import org.study.cinema.repositories.GenreRepository;
import org.study.cinema.repositories.MovieRepository;
import org.study.cinema.repositories.ScheduleRepository;
import org.study.cinema.repositories.TicketRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private TicketRepository ticketRepository;

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

    @Test
    public void shouldReturnAllNoAvailableMovies() {
        List<MovieDto> expectedMoviesDtoList = createTestMovieDtoList().stream()
                .filter(m -> m.getMovieId() < 2)
                .collect(Collectors.toList());

        List<Movie> nonActiveMovieList = createTestMovieList().stream()
                .filter(m -> m.getId() < 2)
                .collect(Collectors.toList());
        nonActiveMovieList.forEach(m -> m.setActive(false));
        when(movieRepository.findByIsActive(false)).thenReturn(nonActiveMovieList);
        List<MovieDto> resultMoviesDtoList = movieServiceImpl.viewAllUnAvailableMovies();

        assertThat(resultMoviesDtoList, equalTo(expectedMoviesDtoList));
    }

    @Test
    public void shouldReturnNullIfAllMoviesAvailable() {
        when(movieRepository.findByIsActive(false)).thenReturn(Collections.emptyList());
        List<MovieDto> resultMoviesDtoList = movieServiceImpl.viewAllUnAvailableMovies();

        assertThat(resultMoviesDtoList, nullValue());
    }

    @Test
    public void shouldCancelMovieById() {
        MovieDto expectedMovieDto = MovieDto.builder()
                .registeredUsers(createTestRegisteredUserList())
                .scheduleList(createTestScheduleList())
                .build();

        int movieId = 1;

        when(scheduleRepository.findAllByIsActiveAndMovieId(false, movieId))
                .thenReturn(createTestScheduleList());
        when(ticketRepository.findAllByScheduleId(1)).thenReturn(createTestTicketList());
        MovieDto resultMovieDto = movieServiceImpl.cancelMovieById(movieId);

        verify(movieRepository).updateByMovieId(movieId);
        verify(scheduleRepository).updateScheduleByMovieId(movieId);
        verify(ticketRepository).findAllByScheduleId(1);
        verify(ticketRepository, times(2)).deleteById(anyInt());
        assertThat(resultMovieDto, equalTo(expectedMovieDto));
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

    private List<Ticket> createTestTicketList() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(Ticket.builder()
                .id(1)
                .registeredUser(createTestRegisteredUserList().get(0))
                .placeNumber(1)
                .placeRow(2)
                .ticketPrice(50.00)
                .build());
        ticketList.add(Ticket.builder()
                .id(2)
                .registeredUser(createTestRegisteredUserList().get(1))
                .placeNumber(2)
                .placeRow(2)
                .ticketPrice(50.00)
                .build());
        return ticketList;
    }

    private List<Schedule> createTestScheduleList() {
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(Schedule.builder()
                .id(1)
                .weekDay(WeekDay.MONDAY)
                .movie(createTestMovieList().get(1))
                .ticketsList(createTestTicketList())
                .time(LocalTime.of(9, 0))
                .build());
        scheduleList.add(Schedule.builder()
                .id(2)
                .weekDay(WeekDay.MONDAY)
                .movie(createTestMovieList().get(1))
                .time(LocalTime.of(12, 0))
                .build());
        scheduleList.add(Schedule.builder()
                .id(3)
                .weekDay(WeekDay.MONDAY)
                .movie(createTestMovieList().get(1))
                .time(LocalTime.of(15, 0))
                .build());
        return scheduleList;
    }

    private List<UserRole> createTestUserRole() {
        UserRole userRole = new UserRole();
        userRole.setUserRole("administrator");

        UserRole adminRole = new UserRole();
        adminRole.setUserRole("user");
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);
        userRoles.add(adminRole);
        return userRoles;
    }

    private List<RegisteredUser> createTestRegisteredUserList() {
        RegisteredUser firstRegisteredUser = new RegisteredUser("Alisa", "Test",
                Gender.FEMALE, "alisa", "a@i.ua", "111");
        firstRegisteredUser.setUserRole(createTestUserRole().get(0));

        RegisteredUser secondRegisteredUser = new RegisteredUser("Anna", "Test",
                Gender.FEMALE, "alisa", "a@i.ua", "111");
        secondRegisteredUser.setUserRole(createTestUserRole().get(0));

        return Arrays.asList(firstRegisteredUser, secondRegisteredUser);
    }
}
