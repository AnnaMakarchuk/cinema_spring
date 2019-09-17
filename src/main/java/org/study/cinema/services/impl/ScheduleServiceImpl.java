package org.study.cinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.cinema.dto.HallDto;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.enums.WeekDay;
import org.study.cinema.exceptions.DataNotFound;
import org.study.cinema.repositories.ScheduleRepository;
import org.study.cinema.services.ScheduleService;
import org.study.cinema.utils.HallDtoConverter;
import org.study.cinema.utils.ScheduleDtoConverter;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleServiceImpl.class);
    private boolean isActive = true;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<ScheduleDto> getAllScheduleByDay(String weekDay) {
        WeekDay day = WeekDay.valueOf(weekDay);
        isActive = true;

        List<Schedule> scheduleListByWeekday = scheduleRepository
                .findAllByWeekDayAndIsActiveOrderByTime(day, isActive);

        if (scheduleListByWeekday.isEmpty()) {
            return null;
        }

        LOGGER.info("ScheduleService return schedule list on " + weekDay);

        return ScheduleDtoConverter.convertScheduleListInScheduleDtoWithTimeList(scheduleListByWeekday);
    }

    @Override
    public List<ScheduleDto> viewNonActiveSchedule() {
        isActive = false;
        List<Schedule> unActiveScheduleList = scheduleRepository.findAllByIsActive(isActive);

        if (unActiveScheduleList.isEmpty()) {
            return null;
        }
        LOGGER.info("ScheduleService return noActive schedule list");

        return ScheduleDtoConverter.convertScheduleListInScheduleDto(unActiveScheduleList);
    }

    @Override
    public HallDto getHallWithPriceAndOccupiedPlacesBySchedule(int scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new DataNotFound("Schedule not found in database"));

        LOGGER.info("ScheduleService return schedule by id " + scheduleId);
        return HallDtoConverter.convertHallDtoWithOccupiedPlacesAndPriceFromSchedule(schedule);
    }
}
