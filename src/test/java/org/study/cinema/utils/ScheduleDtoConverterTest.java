package org.study.cinema.utils;

import org.junit.Before;
import org.junit.Test;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.dto.TimeDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.enums.WeekDay;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleDtoConverterTest {

    private ScheduleDto firstScheduleDtoWithTimeList;
    private ScheduleDto secondScheduleDtoWithTimeList;

    private Schedule firstSchedule;
    private Schedule secondSchedule;
    private Schedule thirdSchedule;

    private ScheduleDto firstScheduleDto;
    private ScheduleDto secondScheduleDto;
    private ScheduleDto thirdScheduleDto;

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
        Movie secondMovie =
                Movie.builder()
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
        secondSchedule = Schedule.builder()
                .id(2)
                .weekDay(WeekDay.MONDAY)
                .movie(secondMovie)
                .time(LocalTime.of(12, 0))
                .build();
        thirdSchedule = Schedule.builder()
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
    }

    @Test
    public void shouldConvertScheduleListDtoWithTimeList() {
        List<ScheduleDto> expectedScheduleDtoList = Arrays
                .asList(firstScheduleDtoWithTimeList, secondScheduleDtoWithTimeList);

        List<ScheduleDto> resultScheduleDtoList = ScheduleDtoConverter.convertScheduleListInScheduleDtoWithTimeList
                (Arrays.asList(firstSchedule, secondSchedule, thirdSchedule));

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }

    @Test
    public void shouldConvertDtoListWithTime() {
        List<ScheduleDto> expectedScheduleDtoList = Arrays
                .asList(firstScheduleDto, secondScheduleDto, thirdScheduleDto);

        List<ScheduleDto> resultScheduleDtoList = ScheduleDtoConverter.convertScheduleListInScheduleDto
                (Arrays.asList(firstSchedule, secondSchedule, thirdSchedule));

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }

    @Test
    public void shouldConvertScheduleInScheduleDto() {
        ScheduleDto expectedScheduleDto = firstScheduleDto;

        ScheduleDto resultScheduleDto = ScheduleDtoConverter.scheduleConverter(firstSchedule);

        assertThat(resultScheduleDto, equalTo(expectedScheduleDto));
    }
}
