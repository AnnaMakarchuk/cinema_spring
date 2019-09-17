package org.study.cinema.unit.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.study.cinema.dto.HallDto;
import org.study.cinema.dto.PlaceDto;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.dto.TimeDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Hall;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.Price;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.Ticket;
import org.study.cinema.entity.enums.WeekDay;
import org.study.cinema.repositories.ScheduleRepository;
import org.study.cinema.services.impl.ScheduleServiceImpl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceImplTest {

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Test
    public void shouldCallGetByDayRepositoryMethod() {
        scheduleService.getAllScheduleByDay("MONDAY");
        verify(scheduleRepository).findAllByWeekDayAndIsActiveOrderByTime(WeekDay.MONDAY, true);
    }

    @Test
    public void shouldReturnListOfSchedulesByDay() {
        List<ScheduleDto> expectedScheduleDtoList = createTestScheduleDtoListWithTimeList();

        when(scheduleRepository.findAllByWeekDayAndIsActiveOrderByTime(WeekDay.MONDAY, true))
                .thenReturn(createTestScheduleList());
        List<ScheduleDto> resultScheduleDtoList = scheduleService.getAllScheduleByDay("MONDAY");

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }

    @Test
    public void shouldReturnNullListByDayIfListFromRepoIsNull() {
        when(scheduleRepository.findAllByWeekDayAndIsActiveOrderByTime(WeekDay.MONDAY, true))
                .thenReturn(Collections.emptyList());

        List<ScheduleDto> resultScheduleDtoList = scheduleService.getAllScheduleByDay("MONDAY");
        assertThat(resultScheduleDtoList, nullValue());
    }

    @Test
    public void shouldCallGetNonActiveRepositoryMethod() {
        scheduleService.viewNonActiveSchedule();
        verify(scheduleRepository).findAllByIsActive(false);
    }

    @Test
    public void shouldReturnNonActiveSchedules() {
        List<ScheduleDto> expectedScheduleDtoList = createTestScheduleDtoListWithTime();

        when(scheduleRepository.findAllByIsActive(false))
                .thenReturn(createTestScheduleList());

        List<ScheduleDto> resultScheduleDtoList = scheduleService.viewNonActiveSchedule();

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }

    @Test
    public void shouldReturnNullNonActiveIfListFromRepoIsNull() {
        when(scheduleRepository.findAllByIsActive(false))
                .thenReturn(Collections.emptyList());

        List<ScheduleDto> resultScheduleDtoList = scheduleService.viewNonActiveSchedule();

        assertThat(resultScheduleDtoList, nullValue());
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfScheduleNotExistInDatabase() throws Exception {
        when(scheduleRepository.findById(1)).thenReturn(Optional.empty());
        scheduleService.getHallWithPriceAndOccupiedPlacesBySchedule(1);
    }

    @Test
    public void shouldConvertHallDtoFromSchedule() throws Exception {
        HallDto expectedHallDto = createTestHallDto();

        when(scheduleRepository.findById(1))
                .thenReturn(Optional.of(createTestScheduleList().get(0)));
        HallDto resultHallDto = scheduleService.getHallWithPriceAndOccupiedPlacesBySchedule(1);

        assertThat(resultHallDto, equalTo(expectedHallDto));
    }

    private Genre createTestGenre() {
        return Genre.builder()
                .id(0)
                .genre("action")
                .build();
    }

    private List<Movie> createTestMovieList() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(Movie.builder()
                .id(0)
                .movieName("Avengers")
                .genre(createTestGenre())
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        movieList.add(Movie.builder()
                .id(1)
                .movieName("Dark")
                .genre(createTestGenre())
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build());
        return movieList;
    }

    private List<Ticket> createTestTicketList() {
        Ticket firstTicket = Ticket.builder()
                .placeNumber(1)
                .placeRow(2)
                .build();
        Ticket secondTicket = Ticket.builder()
                .placeNumber(2)
                .placeRow(2)
                .build();
        return Arrays.asList(firstTicket, secondTicket);
    }

    private Hall createTestHall() {
        Ticket firstTicket = Ticket.builder()
                .placeNumber(1)
                .placeRow(2)
                .build();
        Ticket secondTicket = Ticket.builder()
                .placeNumber(2)
                .placeRow(2)
                .build();
        List<Ticket> tickets = Arrays.asList(firstTicket, secondTicket);
        return Hall.builder()
                .id(1)
                .maxRow(3)
                .maxPlacesInRow(4)
                .hallName("Gold")
                .prices(Collections.singletonList(Price.builder()
                        .id(1)
                        .row(2)
                        .price(50.00)
                        .build()))
                .build();
    }

    private List<Schedule> createTestScheduleList() {
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(Schedule.builder()
                .id(1)
                .weekDay(WeekDay.MONDAY)
                .hall(createTestHall())
                .movie(createTestMovieList().get(0))
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
                .movie(createTestMovieList().get(0))
                .time(LocalTime.of(15, 0))
                .build());
        return scheduleList;
    }

    private List<ScheduleDto> createTestScheduleDtoListWithTimeList() {
        List<ScheduleDto> scheduleDtoList = new ArrayList<>();
        scheduleDtoList.add(ScheduleDto.builder()
                .scheduleId(1)
                .weekDay(WeekDay.MONDAY)
                .movieName(createTestMovieList().get(0).getMovieName())
                .timeList(Arrays.asList(new TimeDto(1, LocalTime.of(9, 0)),
                        new TimeDto(3, LocalTime.of(15, 0))))
                .build());
        scheduleDtoList.add(ScheduleDto.builder()
                .scheduleId(2)
                .weekDay(WeekDay.MONDAY)
                .movieName(createTestMovieList().get(1).getMovieName())
                .timeList(Arrays.asList(new TimeDto(2, LocalTime.of(12, 0))))
                .build());
        return scheduleDtoList;
    }

    private List<ScheduleDto> createTestScheduleDtoListWithTime() {
        List<ScheduleDto> scheduleDtoList = new ArrayList<>();
        scheduleDtoList.add(ScheduleDto.builder()
                .scheduleId(1)
                .weekDay(WeekDay.MONDAY)
                .movieName(createTestMovieList().get(0).getMovieName())
                .ticketList(createTestTicketList())
                .time(createTestScheduleList().get(0).getTime())
                .build());
        scheduleDtoList.add(ScheduleDto.builder()
                .scheduleId(2)
                .weekDay(WeekDay.MONDAY)
                .movieName(createTestMovieList().get(1).getMovieName())
                .time(createTestScheduleList().get(1).getTime())
                .build());
        scheduleDtoList.add(ScheduleDto.builder()
                .scheduleId(3)
                .weekDay(WeekDay.MONDAY)
                .movieName(createTestMovieList().get(0).getMovieName())
                .time(createTestScheduleList().get(2).getTime())
                .build());
        return scheduleDtoList;
    }

    private HallDto createTestHallDto() {
        Price price = Price.builder()
                .id(1)
                .row(2)
                .price(50.00)
                .build();

        return HallDto.builder()
                .hallId(1)
                .maxRow(3)
                .maxPlacesInRow(4)
                .hallName("Gold")
                .schedule(createTestScheduleDtoListWithTime().get(0))
                .occupiedPlaces(Arrays.asList(new PlaceDto(2, 1), new PlaceDto(2, 2)))
                .prices(Collections.singletonList(price))
                .build();
    }
}
