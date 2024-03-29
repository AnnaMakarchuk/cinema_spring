package org.study.cinema.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.study.cinema.dto.HallDto;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.services.impl.ScheduleServiceImpl;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ScheduleServiceImplIntegrationTest {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Test
    public void shouldGetActiveSchedulesByDay() {
        String weekDay = "FRIDAY";
        List<ScheduleDto> resultScheduleList = scheduleService.getAllScheduleByDay(weekDay);

        assertThat("Active Schedule size for Friday should be 4", resultScheduleList, hasSize(4));
    }

    @Test
    public void shouldGetNonActiveSchedules() {
        List<ScheduleDto> resultNoActiveScheduleList = scheduleService.viewNonActiveSchedule();

        assertThat("NonActive Schedule size should be 8", resultNoActiveScheduleList, hasSize(8));
    }

    @Test
    public void shouldGetHallWithPriceAndOccupiedPlacesBySchedule() throws Exception {
        int scheduleId = 34;

        HallDto resultHall = scheduleService.getHallWithPriceAndOccupiedPlacesBySchedule(scheduleId);

        assertThat("Occupied 4 places", resultHall.getOccupiedPlaces(), hasSize(4));
        assertThat(resultHall.getHallName(), equalTo("Blue"));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionForScheduleNotInDatabase() throws Exception {
        int scheduleId = 45;

        HallDto resultHall = scheduleService.getHallWithPriceAndOccupiedPlacesBySchedule(scheduleId);
    }
}
