package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.cinema.dto.MovieDto;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.entity.Schedule;
import org.study.cinema.services.MovieService;
import org.study.cinema.services.ScheduleService;
import org.study.cinema.utils.AttributesNames;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
public class ScheduleController {

    private static final Logger LOGGER = LogManager.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/schedule")
    public String getSchedulePage(@RequestParam(name = AttributesNames.SCHEDULE_DAY) String day,
                                  Model model) {
        List<ScheduleDto> scheduleByDay = scheduleService.getAllScheduleByDay(day.toUpperCase());
        LOGGER.info("Schedule list for " + day + " was obtained");

        model.addAttribute(AttributesNames.SCHEDULE_DAY, day);
        model.addAttribute(AttributesNames.SCHEDULES, scheduleByDay);
        return "schedule_day";
    }

    @GetMapping("admin/unactiveschedule")
    public String viewNonActiveSchedule (Model model) {

        List<ScheduleDto> cancelledScheduleList = scheduleService.viewNonActiveSchedule();
        LOGGER.info("UnActive schedule list was obtained");
        model.addAttribute(AttributesNames.SCHEDULES, cancelledScheduleList);

        List<MovieDto> moviesDto = movieService.viewAllAvailableMovies();
        LOGGER.info("ActiveMovies for non-active schedule was obtained");
        model.addAttribute(AttributesNames.MOVIES, moviesDto);

        return "adminPages/admin_nonactive_schedule";
    }
}
