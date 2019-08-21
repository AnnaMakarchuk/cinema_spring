package org.study.cinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.cinema.dto.HallDto;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.enums.WeekDay;
import org.study.cinema.repositories.ScheduleRepository;
import org.study.cinema.services.ScheduleService;
import org.study.cinema.utils.HallDtoConverter;
import org.study.cinema.utils.ScheduleDtoConverter;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger LOGGER = LogManager.getLogger(ScheduleServiceImpl.class);
    private boolean nonActive = false;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<ScheduleDto> getAllScheduleByDay(String weekDay) {
        WeekDay day = WeekDay.valueOf(weekDay);
        List<Schedule> scheduleListByWeekday = scheduleRepository
                .findAllByWeekDayOrderByTime(day);

        if (scheduleListByWeekday.isEmpty()) {
            return null;
        }

        LOGGER.info("ScheduleService return schedule list on " + weekDay);

        return ScheduleDtoConverter.convertScheduleListInScheduleDtoWithTimeList(scheduleListByWeekday);
    }

    @Override
    public List<ScheduleDto> viewNonActiveSchedule() {
        List<Schedule> unActiveScheduleList = scheduleRepository.findAllByIsActive(nonActive);

        if (unActiveScheduleList.isEmpty()) {
            return null;
        }
        LOGGER.info("ScheduleService return noActive schedule list");

        return ScheduleDtoConverter.convertScheduleListInScheduleDto(unActiveScheduleList);
    }

    @Override
    public ScheduleDto getScheduleById(int id) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
        LOGGER.info("ScheduleService return schedule by id " + id);
        return scheduleOptional.map(ScheduleDtoConverter::scheduleConverter).orElse(null);
    }

    @Override
    public HallDto getHallWithPriceAndOccupiedPlacesBySchedule(int scheduleId) throws Exception {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()->new Exception("Schedule with such id not found"));

        LOGGER.info("ScheduleService return schedule by id " + scheduleId);

        return HallDtoConverter.convertHallDtoWithOccupiedPlacesAndPriceFromSchedule(schedule);
    }

}
