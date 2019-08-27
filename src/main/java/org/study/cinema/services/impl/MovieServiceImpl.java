package org.study.cinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.Ticket;
import org.study.cinema.repositories.GenreRepository;
import org.study.cinema.repositories.MovieRepository;
import org.study.cinema.repositories.ScheduleRepository;
import org.study.cinema.repositories.TicketRepository;
import org.study.cinema.services.MovieService;
import org.study.cinema.utils.MovieDtoConverter;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LogManager.getLogger(MovieServiceImpl.class);
    private boolean isActive;
    private MovieRepository movieRepository;
    private GenreRepository genreRepository;
    private ScheduleRepository scheduleRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository,
                            ScheduleRepository scheduleRepository, TicketRepository ticketRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.scheduleRepository = scheduleRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<MovieDto> viewAllAvailableMovies() {
        isActive = true;
        List<Movie> activeMovies = movieRepository.findByIsActive(isActive);
        if (activeMovies.isEmpty()) {
            return null;
        }
        LOGGER.info("MovieService return list of active movies from database");
        return MovieDtoConverter.convertMovieListInMovieDtoList(activeMovies);
    }

    @Override
    public void addNewMovie(MovieDto movieDto) {
        Genre genre = generateGenre(movieDto);
        Movie movie = MovieDtoConverter.convertMovieDtoInMovie(movieDto, genre);
        LOGGER.info("Movie is prepared for saving to database" + movieDto.toString());
        movieRepository.save(movie);
    }

    @Override
    public List<MovieDto> viewAllUnAvailableMovies() {
        isActive = false;
        List<Movie> unActiveMovies = movieRepository.findByIsActive(isActive);
        if (unActiveMovies.isEmpty()) {
            return null;
        }
        LOGGER.info("MovieService return list of active movies from database");
        return MovieDtoConverter.convertMovieListInMovieDtoList(unActiveMovies);
    }

    @Override
    public MovieDto cancelMovieById(int movieId) {
        movieRepository.updateByMovieId(movieId);
        LOGGER.info("Movie is updated in database. Movie status is no active " + movieId);

        isActive = false;
        scheduleRepository.updateScheduleByMovieId(movieId);
        List<Schedule> cancelledScheduleList = scheduleRepository.findAllByIsActiveAndMovieId(isActive, movieId);
        LOGGER.info("Schedules are updated with status no active. List are selected from database "
                + cancelledScheduleList.toString());

        List<Ticket> deletedTickets = getTicketsForCancelledSchedule(cancelledScheduleList);
        LOGGER.info("Movie Service returned list of tickets for cancelled movie" + deletedTickets.toString());

        List<RegisteredUser> registeredUsers = getUsersWithCancelledTickets(deletedTickets);
        LOGGER.info("List of users for notification is created " + registeredUsers.toString());

        deletedTickets.forEach(t -> ticketRepository.deleteById(t.getId()));
        LOGGER.info("Tickets was deleted");

        return MovieDto.builder()
                .registeredUsers(registeredUsers)
                .scheduleList(cancelledScheduleList)
                .build();
    }

    private List<RegisteredUser> getUsersWithCancelledTickets(List<Ticket> deletedTickets) {
        return deletedTickets.stream()
                .map(Ticket::getRegisteredUser)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<Ticket> getTicketsForCancelledSchedule(List<Schedule> cancelledScheduleList) {
        List<Ticket> deletedTickets = new ArrayList<>();
        for (Schedule schedule : cancelledScheduleList) {
            deletedTickets.addAll(ticketRepository.findAllByScheduleId(schedule.getId()));
        }
        return deletedTickets;
    }

    private Genre generateGenre(MovieDto movieDto) {
        String genreName = movieDto.getMovieGenre();
        LOGGER.info("genre is " + genreName) ;
        int genreId = genreRepository.findByGenre(genreName);
        return Genre.builder().
                id(genreId)
                .genre(genreName)
                .build();
    }
}
