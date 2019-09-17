package org.study.cinema.integration.repositories;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.enums.WeekDay;
import org.study.cinema.repositories.ScheduleRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class ScheduleRepositoryIntegrationTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    public void shouldGetScheduleByDayOrderByTime() {
        List<LocalTime> expectedScheduleTime = Arrays
                .asList(LocalTime.of(9, 0), LocalTime.of(10, 30),
                        LocalTime.of(15, 30), LocalTime.of(19, 30),
                        LocalTime.of(22, 0));

        WeekDay weekDay = WeekDay.FRIDAY;
        boolean isActive = true;
        List<Schedule> resultScheduleList = scheduleRepository.findAllByWeekDayAndIsActiveOrderByTime(weekDay, isActive);
        List<LocalTime> resultScheduleTime = resultScheduleList.stream()
                .map(Schedule::getTime)
                .collect(Collectors.toList());

        assertThat("Active Schedule size for Friday should be 5", resultScheduleList, hasSize(5));
        assertThat(resultScheduleTime, equalTo(expectedScheduleTime));
    }

    @Test
    public void shouldGetAllNonActiveSchedule() {
        boolean isActive = false;
        List<Schedule> resultScheduleList = scheduleRepository.findAllByIsActive(isActive);

        assertThat("NonActive Schedule size for should be 8", resultScheduleList, hasSize(8));
    }

    @Test
    public void shouldGetAllActiveScheduleForMovieWithId() {
        List<Schedule> expectedScheduleListForMovieId = createTestScheduleByMovieId();

        boolean isActive = true;
        int movieId = 3;
        List<Schedule> resultScheduleList = scheduleRepository.findAllByIsActiveAndMovieId(isActive, movieId);
        List<Schedule> resultScheduleListForMovieId = resultScheduleList.stream()
                .map(s ->
                        Schedule.builder()
                                .weekDay(s.getWeekDay())
                                .time(s.getTime()).build())
                .collect(Collectors.toList());

        assertThat("NonActive Schedule size for should be 7", resultScheduleList, hasSize(7));
        assertThat(resultScheduleListForMovieId, equalTo(expectedScheduleListForMovieId));
    }

    @Test
    public void shouldUpdateScheduleByMovieId() {

        int movieIdUnActive = 4;
        boolean isActiveSchedule = false;

        scheduleRepository.updateScheduleByMovieId(4);

        List<Schedule> resultScheduleList = scheduleRepository
                .findAllByIsActiveAndMovieId(isActiveSchedule, movieIdUnActive);
        List<Boolean> active = resultScheduleList.stream()
                .map(Schedule::isActive)
                .distinct()
                .collect(Collectors.toList());

        assertThat("NonActive Schedule for movie id 4 size for should be 9", resultScheduleList, hasSize(9));
        assertThat(active, hasSize(1));
        assertThat(active.get(0), Matchers.equalTo(false));
    }

    private List<Schedule> createTestScheduleByMovieId() {
        List<Schedule> expectedScheduleList = new ArrayList<>();
        expectedScheduleList.add(Schedule.builder()
                .weekDay(WeekDay.MONDAY)
                .time(LocalTime.of(9, 0))
                .build());
        expectedScheduleList.add(Schedule.builder()
                .weekDay(WeekDay.TUESDAY)
                .time(LocalTime.of(9, 0))
                .build());
        expectedScheduleList.add(Schedule.builder()
                .weekDay(WeekDay.WEDNESDAY)
                .time(LocalTime.of(9, 0))
                .build());
        expectedScheduleList.add(Schedule.builder()
                .weekDay(WeekDay.THURSDAY)
                .time(LocalTime.of(9, 0))
                .build());
        expectedScheduleList.add(Schedule.builder()
                .weekDay(WeekDay.FRIDAY)
                .time(LocalTime.of(9, 0))
                .build());
        expectedScheduleList.add(Schedule.builder()
                .weekDay(WeekDay.SATURDAY)
                .time(LocalTime.of(9, 0))
                .build());
        expectedScheduleList.add(Schedule.builder()
                .weekDay(WeekDay.SUNDAY)
                .time(LocalTime.of(9, 0))
                .build());
        return expectedScheduleList;
    }
}
