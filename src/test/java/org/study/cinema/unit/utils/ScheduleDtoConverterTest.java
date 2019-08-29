package org.study.cinema.unit.utils;

import org.junit.Test;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.dto.TimeDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.enums.WeekDay;
import org.study.cinema.utils.ScheduleDtoConverter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleDtoConverterTest {

    @Test
    public void shouldConvertScheduleListDtoWithTimeList() {
        List<ScheduleDto> expectedScheduleDtoList = createTestScheduleDtoListWithTimeList();

        List<ScheduleDto> resultScheduleDtoList = ScheduleDtoConverter.convertScheduleListInScheduleDtoWithTimeList
                (createTestScheduleList());

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }

    @Test
    public void shouldConvertDtoListWithTime() {
        List<ScheduleDto> expectedScheduleDtoList = createTestScheduleDtoListWithTime();

        List<ScheduleDto> resultScheduleDtoList = ScheduleDtoConverter.convertScheduleListInScheduleDto
                (createTestScheduleList());

        assertThat(resultScheduleDtoList, equalTo(expectedScheduleDtoList));
    }

    @Test
    public void shouldConvertScheduleInScheduleDto() {
        ScheduleDto expectedScheduleDto = createTestScheduleDtoListWithTime().get(0);

        ScheduleDto resultScheduleDto = ScheduleDtoConverter.scheduleConverter(createTestScheduleList().get(0));

        assertThat(resultScheduleDto, equalTo(expectedScheduleDto));
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

    private List<Schedule> createTestScheduleList() {
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(Schedule.builder()
                .id(1)
                .weekDay(WeekDay.MONDAY)
                .movie(createTestMovieList().get(0))
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
}
