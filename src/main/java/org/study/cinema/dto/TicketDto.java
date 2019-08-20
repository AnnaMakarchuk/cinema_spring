package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.study.cinema.entity.RegisteredUser;
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
    private int userId;
    private String movieName;
    private WeekDay weekDay;
    private LocalTime scheduleTime;
    private String hallName;
    private int placeRow;
    private int placeNumber;
    private double ticketPrice;

    private int scheduleId;
    private RegisteredUser registeredUser;

    public TicketDto(int ticketId, int placeRow, int placeNumber, double ticketPrice, int userId, WeekDay weekDay,
                     LocalTime scheduleTime, String movieName, String hallName) {
        this.ticketId = ticketId;
        this.placeRow = placeRow;
        this.placeNumber = placeNumber;
        this.ticketPrice = ticketPrice;
        this.userId = userId;
        this.weekDay = weekDay;
        this.scheduleTime = scheduleTime;
        this.movieName = movieName;
        this.hallName = hallName;
    }
}
