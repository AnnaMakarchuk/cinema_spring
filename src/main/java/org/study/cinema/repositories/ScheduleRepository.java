package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.enums.WeekDay;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Optional<Schedule>> findAllByWeekDayOrderByTime (WeekDay weekDay);

}
