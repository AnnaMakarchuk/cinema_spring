package org.study.cinema.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketDtoConverter {
    private static final Logger LOGGER = LogManager.getLogger(TicketDtoConverter.class);

    public static List<TicketDto> convertTicketsPageInTicketsDto(Page<Ticket> ticketsOnPage) {
        List<TicketDto> ticketDtoList = ticketsOnPage.stream()
                .map(TicketDtoConverter::ticketConverter)
                .collect(Collectors.toList());
        LOGGER.info("Pages with tickets was converted in TicketDto List");
        return ticketDtoList;
    }

    private static TicketDto ticketConverter(Ticket ticket) {
        return TicketDto.builder()
                .ticketId(ticket.getId())
                .hallName(ticket.getSchedule().getHall().getHallName())
                .movieName(ticket.getSchedule().getMovie().getMovieName())
                .placeNumber(ticket.getPlaceNumber())
                .placeRow(ticket.getPlaceRow())
                .scheduleTime(ticket.getSchedule().getTime())
                .weekDay(ticket.getSchedule().getWeekDay())
                .ticketPrice(ticket.getTicketPrice())
                .build();
    }
}
