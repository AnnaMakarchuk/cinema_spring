package org.study.cinema.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.Ticket;

import java.util.Optional;
import java.util.stream.Collectors;

public class TicketDtoConverter {
    private static final Logger LOGGER = LogManager.getLogger(TicketDtoConverter.class);

    public static Page<TicketDto> convertTicketsPageInTicketsDto(Page<Ticket> ticketsOnPage) {
        return (Page<TicketDto>) ticketsOnPage.stream()
                .map(TicketDtoConverter::ticketConverter)
                .collect(Collectors.toList());
    }

    private static TicketDto ticketConverter(Ticket ticket) {
        TicketDto ticketDto = TicketDto.builder()
                .ticketId(ticket.getId())
                .hallName(ticket.getSchedule().getHall().getHallName())
                .movieName(ticket.getSchedule().getMovie().getMovieName())
                .placeNumber(ticket.getPlaceNumber())
                .placeRow(ticket.getPlaceRow())
                .scheduleTime(ticket.getSchedule().getTime())
                .weekDay(ticket.getSchedule().getWeekDay())
                .ticketPrice(ticket.getTicketPrice())
                .build();
        LOGGER.info("Ticket was converted in TicketDto ");
        return ticketDto;
    }
}



