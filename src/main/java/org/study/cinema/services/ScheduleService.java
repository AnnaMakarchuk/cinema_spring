package org.study.cinema.services;

import org.study.cinema.dto.HallDto;
import org.study.cinema.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> getAllScheduleByDay(String weekDay);

    List<ScheduleDto> viewNonActiveSchedule();

    HallDto getHallWithPriceAndOccupiedPlacesBySchedule(int scheduleId) throws Exception;
}
