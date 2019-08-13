package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.study.cinema.entity.enums.WeekDay;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TicketDto {

    private int ticketId;
    private double ticketPrice;
    private String movieName;
    private WeekDay weekDay;
    private LocalTime scheduleTime;
    private String hallName;
    private int placeRow;
    private int placeNumber;
}
