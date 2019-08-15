package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.services.ScheduleService;
import org.study.cinema.utils.AttributesNames;

import java.util.List;
import java.util.Objects;

@Controller
public class ScheduleController {

    private static final Logger LOGGER = LogManager.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/schedule")
    public String getSchedulePage(@RequestParam(name = AttributesNames.SCHEDULE_DAY) String day,
                                  Model model) {
        List<ScheduleDto> scheduleByDay = scheduleService.getAllScheduleByDay(day.toUpperCase());
        LOGGER.info("Schedule list for " + day + " was obtained");

        model.addAttribute(AttributesNames.SCHEDULE_DAY, day);
        model.addAttribute(AttributesNames.SCHEDULES, scheduleByDay);
        return "schedule_day";
    }
}
