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
import java.util.stream.Collectors;

public class ScheduleDtoConverter {

    private static final Logger LOGGER = LogManager.getLogger(ScheduleDtoConverter.class);
    private static boolean uniqueMovie;

    public static List<ScheduleDto> convertScheduleListInScheduleDto(List<Schedule> scheduleList) {
        List<ScheduleDto> scheduleDtoList = scheduleList.stream()
                .map(ScheduleDtoConverter::scheduleConverter)
                .collect(Collectors.toList());
        LOGGER.info("Schedule list was converted in ScheduleDto list");
        return scheduleDtoList;
    }

    public static List<ScheduleDto> convertScheduleListInScheduleDtoWithTimeList
            (List<Schedule> scheduleList) {
        return createScheduleDtoListWithRepeatedMoviesDuringDay(scheduleList);
    }

    public static ScheduleDto scheduleConverter(Schedule schedule) {
        return ScheduleDto.builder()
                .scheduleId(schedule.getId())
                .movieName(schedule.getMovie().getMovieName())
                .weekDay(schedule.getWeekDay())
                .time(schedule.getTime())
                .build();
    }

    private static List<ScheduleDto> createScheduleDtoListWithRepeatedMoviesDuringDay(List<Schedule> schedules) {
        List<ScheduleDto> scheduleDtoList = new ArrayList<>();

        for (int i = 0; i < schedules.size(); i++) {

            int id = schedules.get(i).getId();
            WeekDay weekday = schedules.get(i).getWeekDay();
            String movieName = schedules.get(i).getMovie().getMovieName();
            LocalTime time = schedules.get(i).getTime();

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
