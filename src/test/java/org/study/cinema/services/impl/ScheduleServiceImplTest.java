package org.study.cinema.services.impl;

import org.junit.Before;
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

import java.time.LocalTime;
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

    private Schedule firstSchedule;

    private ScheduleDto firstScheduleDtoWithTimeList;
    private ScheduleDto secondScheduleDtoWithTimeList;

    private ScheduleDto firstScheduleDto;
    private ScheduleDto secondScheduleDto;
    private ScheduleDto thirdScheduleDto;

    private HallDto hallDto;

    private List<Optional<Schedule>> optionalsSchedulesWithTimeList;

    @Before
    public void setUp() {
        Genre genre = Genre.builder()
                .id(0)
                .genre("action")
                .build();
        Movie firstMovie = Movie.builder()
                .id(0)
                .movieName("Avengers")
                .genre(genre)
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build();
        Movie secondMovie = Movie.builder()
                .id(1)
                .movieName("Dark")
                .genre(genre)
                .movieDuration(100)
                .ageLimit(16)
                .movieDescription("no")
                .build();
        firstSchedule = Schedule.builder()
                .id(1)
                .weekDay(WeekDay.MONDAY)
                .movie(firstMovie)
                .time(LocalTime.of(9, 0))
                .build();
        Schedule secondSchedule = Schedule.builder()
                .id(2)
                .weekDay(WeekDay.MONDAY)
                .movie(secondMovie)
                .time(LocalTime.of(12, 0))
                .build();
        Schedule thirdSchedule = Schedule.builder()
                .id(3)
                .weekDay(WeekDay.MONDAY)
                .movie(firstMovie)
                .time(LocalTime.of(15, 0))
                .build();

        firstScheduleDtoWithTimeList = ScheduleDto.builder()
                .scheduleId(1)
                .weekDay(WeekDay.MONDAY)
                .movieName(firstMovie.getMovieName())
                .timeList(Arrays.asList(new TimeDto(1, LocalTime.of(7, 0)),
                        new TimeDto(3, LocalTime.of(13, 0))))
                .build();
        secondScheduleDtoWithTimeList = ScheduleDto.builder()
                .scheduleId(2)
                .weekDay(WeekDay.MONDAY)
                .movieName(secondMovie.getMovieName())
                .timeList(Arrays.asList(new TimeDto(2, LocalTime.of(10, 0))))
                .build();

        firstScheduleDto = ScheduleDto.builder()
                .scheduleId(1)
                .weekDay(WeekDay.MONDAY)
                .movieName(firstMovie.getMovieName())
                .time(firstSchedule.getTime())
                .build();
        secondScheduleDto = ScheduleDto.builder()
                .scheduleId(2)
                .weekDay(WeekDay.MONDAY)
                .movieName(secondMovie.getMovieName())
                .time(secondSchedule.getTime())
                .build();
        thirdScheduleDto = ScheduleDto.builder()
                .scheduleId(3)
                .weekDay(WeekDay.MONDAY)
                .movieName(firstMovie.getMovieName())
                .time(thirdSchedule.getTime())
                .build();

        optionalsSchedulesWithTimeList = Arrays.asList(Optional.of(firstSchedule),
                Optional.of(secondSchedule),
                Optional.of(thirdSchedule));

        Price price = Price.builder()
                .id(1)
                .row(2)
                .price(50.00)
                .build();

        hallDto = HallDto.builder()
                .hallId(1)
                .maxRow(3)
                .maxPlacesInRow(4)
                .hallName("Gold")
                .occupiedPlaces(Arrays.asList(new PlaceDto(2, 1), new PlaceDto(2, 2)))
                .prices(Collections.singletonList(price))
                .build();
    }

    @Test
    public void shouldCallGetByDayRepositoryMethod() {
        scheduleService.getAllScheduleByDay("MONDAY");
        verify(scheduleRepository).findAllByWeekDayOrderByTime(WeekDay.MONDAY);
    }

    @Test
    public void shouldReturnListOfSchedulesByDay() {
        List<ScheduleDto> expectedScheduleDtoList = Arrays
                .asList(firstScheduleDtoWithTimeList, secondScheduleDtoWithTimeList);

        when(scheduleRepository.findAllByWeekDayOrderByTime(WeekDay.MONDAY))
                .thenReturn(optionalsSchedulesWithTimeList);
        List<ScheduleDto> resultScheduleDtoList = scheduleService.getAllScheduleByDay("MONDAY");

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }

    @Test
    public void shouldReturnNullListByDayIsListFromRepoIsNull() {
        when(scheduleRepository.findAllByWeekDayOrderByTime(WeekDay.MONDAY))
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
        List<ScheduleDto> expectedScheduleDtoList = Arrays.asList(firstScheduleDto, secondScheduleDto, thirdScheduleDto);

        when(scheduleRepository.findAllByIsActive(false))
                .thenReturn(optionalsSchedulesWithTimeList);

        List<ScheduleDto> resultScheduleDtoList = scheduleService.viewNonActiveSchedule();

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }

    @Test
    public void shouldReturnNullNonActiveListFromRepoIsNull() {
        when(scheduleRepository.findAllByIsActive(false))
                .thenReturn(Collections.emptyList());

        List<ScheduleDto> resultScheduleDtoList = scheduleService.viewNonActiveSchedule();

        assertThat(resultScheduleDtoList, nullValue());
    }

    @Test
    public void shouldGetScheduleById() {
        ScheduleDto expectedScheduleDto = firstScheduleDto;

        when(scheduleRepository.findById(1))
                .thenReturn(Optional.of(firstSchedule));

        ScheduleDto resultScheduleDto = scheduleService.getScheduleById(1);

        assertThat(resultScheduleDto, equalTo(expectedScheduleDto));
    }

    @Test
    public void shouldReturnNullIfScheduleNotExistInDatabase() throws Exception {
        when(scheduleRepository.findById(1)).thenReturn(Optional.empty());
        assertThat(scheduleService.getScheduleById(1), nullValue());
        assertThat(scheduleService.getHallWithPriceAndOccupiedPlacesBySchedule(1), nullValue());
    }

    @Test
    public void shouldConvertHallDtoFromSchedule() throws Exception {
        HallDto expectedHallDto = hallDto;

        when(scheduleRepository.findById(1))
                .thenReturn(Optional.of(firstSchedule));
        Ticket firstTicket = Ticket.builder()
                .placeNumber(1)
                .placeRow(2)
                .build();
        Ticket secondTicket = Ticket.builder()
                .placeNumber(2)
                .placeRow(2)
                .build();
        List<Ticket> tickets = Arrays.asList(firstTicket, secondTicket);
        Hall hall = Hall.builder()
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
        firstSchedule.setTicketsList(tickets);
        firstSchedule.setHall(hall);
        HallDto resultHallDto = scheduleService.getHallWithPriceAndOccupiedPlacesBySchedule(1);

        assertThat(resultHallDto, equalTo(expectedHallDto));
    }
}
