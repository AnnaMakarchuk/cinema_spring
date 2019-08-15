package org.study.cinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.enums.WeekDay;
import org.study.cinema.repositories.ScheduleRepository;
import org.study.cinema.services.ScheduleService;
import org.study.cinema.utils.ScheduleDtoConverter;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleServiceImpl.class);


    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<ScheduleDto> getAllScheduleByDay(String weekDay) {
        WeekDay day = WeekDay.valueOf(weekDay);
        List<Optional<Schedule>> scheduleListByWeekday = scheduleRepository
                .findAllByWeekDayOrderByTime(day);

        if (scheduleListByWeekday.isEmpty()) {
            return null;
        }

        LOGGER.info("ScheduleService return schedule list on " + weekDay);

        return ScheduleDtoConverter.convertScheduleListInScheduleDtoList(scheduleListByWeekday);
    }
}
