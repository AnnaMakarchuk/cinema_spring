package org.study.cinema.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.cinema.dto.HallDto;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.exceptions.DataNotFound;
import org.study.cinema.services.MovieService;
import org.study.cinema.services.ScheduleService;
import org.study.cinema.utils.AttributesNames;

import java.util.List;

@Controller
public class ScheduleController {

    private static final Logger LOGGER = LogManager.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MovieService movieService;

    //    TODO check page and js.
    @GetMapping(value = {"/schedule", "/schedule/{day}"})
    public String getSchedulePage(@RequestParam(name = AttributesNames.SCHEDULE_DAY, defaultValue = "MONDAY") String day,
                                  Model model) {
        List<ScheduleDto> scheduleByDay = scheduleService.getAllScheduleByDay(day.toUpperCase());
        LOGGER.info("Schedule list for " + day + " was obtained");

        model.addAttribute(AttributesNames.SCHEDULE_DAY, day);
        model.addAttribute(AttributesNames.SCHEDULES, scheduleByDay);
        return "schedule_day";
    }

    @GetMapping("/hallscheme")
    public String getHallScheme(@RequestParam(name = AttributesNames.SCHEDULE_ID) int scheduleId,
                                Model model) throws Exception {
        HallDto hallDto = scheduleService.getHallWithPriceAndOccupiedPlacesBySchedule(scheduleId);
        LOGGER.info("Hall scheme for schedule id " + scheduleId + "was get");
        model.addAttribute(AttributesNames.HALL, hallDto);
        model.addAttribute(AttributesNames.SCHEDULE, hallDto.getSchedule());

        ObjectMapper mapper = new ObjectMapper();
        try {
            model.addAttribute(AttributesNames.OCCUPIED_PLACES,
                    mapper.writeValueAsString(hallDto.getOccupiedPlaces()));
        } catch (JsonProcessingException e) {
            LOGGER.error(" Cannot convert JSON to String, Json exception: ", e);
        }
        return "hall_scheme";
    }

    @GetMapping("admin/unactiveschedule")
    public String getNonActiveSchedule(Model model) {

        List<ScheduleDto> cancelledScheduleList = scheduleService.viewNonActiveSchedule();
        LOGGER.info("UnActive schedule list was obtained");
        model.addAttribute(AttributesNames.SCHEDULES, cancelledScheduleList);

        List<MovieDto> moviesDto = movieService.viewAllAvailableMovies();
        LOGGER.info("ActiveMovies for non-active schedule was obtained");
        model.addAttribute(AttributesNames.MOVIES, moviesDto);

        return "adminPages/admin_nonactive_schedule";
    }
}
