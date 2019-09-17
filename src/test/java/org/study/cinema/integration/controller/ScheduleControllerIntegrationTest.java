package org.study.cinema.integration.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.dto.HallDto;
import org.study.cinema.services.impl.ScheduleServiceImpl;
import org.study.cinema.utils.AttributesNames;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
@AutoConfigureMockMvc
public class ScheduleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Test
    public void shouldReturnPageWithScheduleByDay() throws Exception {
        String day = "MONDAY";
        mockMvc.perform(get("/schedule")
                .param(AttributesNames.SCHEDULE_DAY, day))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors())
                .andExpect(model().attribute(AttributesNames.SCHEDULE_DAY, day))
                .andExpect(model().attribute(AttributesNames.SCHEDULES, scheduleService.getAllScheduleByDay(day)));
    }

    @Test
    public void shouldReturnHallScheme() throws Exception {
        String scheduleId = "4";
        mockMvc.perform((get("/hallscheme"))
                .param(AttributesNames.SCHEDULE_ID, scheduleId))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors())
                .andExpect(model().attribute(AttributesNames.HALL, equalTo(createHallDto())))
                .andExpect(model().attribute(AttributesNames.SCHEDULE, equalTo(createHallDto().getSchedule())));
    }

    @Test
    public void shouldReturnNoActiveSchedule() throws Exception {
        mockMvc.perform(get("/admin/unactiveschedule"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasNoErrors())
                .andExpect(model().attribute(AttributesNames.SCHEDULES, scheduleService.viewNonActiveSchedule()));
    }

    private HallDto createHallDto() {
        return scheduleService.getHallWithPriceAndOccupiedPlacesBySchedule(4);
    }
}
