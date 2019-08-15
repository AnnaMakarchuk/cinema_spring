package org.study.cinema.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.cinema.dto.ScheduleDto;
import org.study.cinema.dto.TimeDto;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.enums.WeekDay;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScheduleDtoConverter {

    private static final Logger LOGGER = LogManager.getLogger(ScheduleDtoConverter.class);
    private static boolean uniqueMovie;

    public static List<ScheduleDto> convertScheduleListInScheduleDto(List<Optional<Schedule>> scheduleOptionalList) {
        return scheduleOptionalList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ScheduleDtoConverter::scheduleConverter)
                .collect(Collectors.toList());
    }

    public static List<ScheduleDto> convertScheduleListInScheduleDtoWithTimeList
            (List<Optional<Schedule>> scheduleOptionalList) {
        List<Schedule> schedules = convertScheduleListFromOptional(scheduleOptionalList);

        return createScheduleDtoListWithRepeatedMoviesDuringDay(schedules);
    }

    private static ScheduleDto scheduleConverter(Schedule schedule) {
        ScheduleDto scheduleDto = ScheduleDto.builder()
                .scheduleId(schedule.getId())
                .movieName(schedule.getMovie().getMovieName())
                .weekDay(schedule.getWeekDay())
                .time(schedule.getTime())
                .build();
        LOGGER.info("Schedule was converted in ScheduleDto " + scheduleDto.toString());
        return scheduleDto;
    }

    private static List<Schedule> convertScheduleListFromOptional(List<Optional<Schedule>> scheduleOptionalList) {
        return scheduleOptionalList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private static List<ScheduleDto> createScheduleDtoListWithRepeatedMoviesDuringDay(List<Schedule> schedules) {
        List<ScheduleDto> scheduleDtoList = new ArrayList<>();

        for (int i = 0; i < schedules.size(); i++) {

            int id = schedules.get(i).getId();
            WeekDay weekday = schedules.get(i).getWeekDay();
            String movieName = schedules.get(i).getMovie().getMovieName();
            LocalTime time = schedules.get(i).getTime().minusHours(2);

            if (i == 0) {
                scheduleDtoList.add(addElementToScheduleDTOListWithTimeList(id, weekday, movieName, time));
            } else {
                uniqueMovie = true;

                for (ScheduleDto scheduleDto : scheduleDtoList) {
                    if (checkRepeatMovieInSchedule(movieName, scheduleDto)) {
                        scheduleDto.getTimeList().add(new TimeDto(id, time));
                        uniqueMovie = false;
                    }
                }

                if (uniqueMovie) {
                    scheduleDtoList.add(addElementToScheduleDTOListWithTimeList(id, weekday, movieName, time));
                }
            }
        }
        LOGGER.info("ScheduleDtoList with repeated movies and timeList was converted");
        return scheduleDtoList;
    }

    private static boolean checkRepeatMovieInSchedule(String movieName, ScheduleDto scheduleDto) {
        return scheduleDto.getMovieName().equals(movieName);
    }

    private static ScheduleDto addElementToScheduleDTOListWithTimeList
            (int scheduleId, WeekDay weekday, String movieName, LocalTime time) {
        return ScheduleDto.builder()
                .scheduleId(scheduleId)
                .movieName(movieName)
                .weekDay(weekday)
                .timeList(getTimeList(scheduleId, time))
                .build();
    }

    private static List<TimeDto> getTimeList(int scheduleId, LocalTime time) {
        List<TimeDto> timeList = new ArrayList<>();
        timeList.add(new TimeDto(scheduleId, time));
        return timeList;
    }
}
