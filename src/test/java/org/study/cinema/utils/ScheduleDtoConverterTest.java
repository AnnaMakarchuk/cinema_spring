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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleDtoConverterTest {

    private ScheduleDto firstScheduleDto;
    private ScheduleDto secondScheduleDto;
    private List<Optional<Schedule>> optionalsSchedules;
    private List<Optional<Schedule>> optionalsWithEmptySchedule;

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
        Schedule firstSchedule = Schedule.builder()
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

        firstScheduleDto = ScheduleDto.builder()
                .id(1)
                .weekDay(WeekDay.MONDAY)
                .movieName(firstMovie.getMovieName())
                .timeList(Arrays.asList(new TimeDto(1, LocalTime.of(7, 0)),
                        new TimeDto(3, LocalTime.of(13, 0))))
                .build();
        secondScheduleDto = ScheduleDto.builder()
                .id(2)
                .weekDay(WeekDay.MONDAY)
                .movieName(secondMovie.getMovieName())
                .timeList(Arrays.asList(new TimeDto(2, LocalTime.of(10, 0))))
                .build();

        optionalsSchedules = Arrays.asList(Optional.of(firstSchedule),
                Optional.of(secondSchedule),
                Optional.of(thirdSchedule));

        optionalsWithEmptySchedule = Arrays.asList(Optional.of(firstSchedule),
                Optional.empty(),
                Optional.of(secondSchedule),
                Optional.of(thirdSchedule));
    }

    @Test
    public void shouldConvertScheduleListDto() {
        List<ScheduleDto> expectedScheduleDtoList = Arrays.asList(firstScheduleDto, secondScheduleDto);

        List<ScheduleDto> resultScheduleDtoList = ScheduleDtoConverter.convertScheduleListInScheduleDtoList
                (optionalsSchedules);

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }

    @Test
    public void shouldConvertScheduleDtoListWithOneEmptyOptional() {
        List<ScheduleDto> expectedScheduleDtoList = Arrays.asList(firstScheduleDto, secondScheduleDto);

        List<ScheduleDto> resultScheduleDtoList = ScheduleDtoConverter.convertScheduleListInScheduleDtoList
                (optionalsWithEmptySchedule);

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }
}