package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.enums.WeekDay;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findAllByWeekDayOrderByTime(WeekDay weekDay);

    List<Schedule> findAllByIsActive(boolean nonActive);

    List<Schedule> findAllByIsActiveAndMovieId(boolean isActive, int movieId);

    @Modifying
    @Query("Update Schedule s SET s.isActive = false WHERE s.movie.id = :movieId")
    void updateScheduleByMovieId(@Param("movieId") int movieId);
}
