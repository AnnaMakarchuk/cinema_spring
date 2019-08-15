package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.study.cinema.entity.Ticket;
import org.study.cinema.entity.enums.WeekDay;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ScheduleDto {
    private int scheduleId;
    private WeekDay weekDay;
    private LocalTime time;
    private List<TimeDto> timeList;
    private String movieName;
    private List<Ticket> ticketList;
}
