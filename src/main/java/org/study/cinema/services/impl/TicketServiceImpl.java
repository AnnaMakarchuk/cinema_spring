package org.study.cinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.cinema.dto.PositionDto;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.Ticket;
import org.study.cinema.repositories.TicketRepository;
import org.study.cinema.services.TicketService;
import org.study.cinema.utils.TicketDtoConverter;
import org.study.cinema.utils.UserDtoConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = LogManager.getLogger(TicketServiceImpl.class);
    private static final int ROW_ON_PAGE = 5;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public int countPagesQuantity() {
        return (int) Math.ceil(ticketRepository.count() / (double) ROW_ON_PAGE);
    }

    @Override
    public List<TicketDto> allTicketsWithPagination(int page) {
        Pageable pageable = PageRequest.of(page - 1, ROW_ON_PAGE, Sort.by(
                Sort.Order.asc("id")));
        Page<Ticket> ticketsOnPage = ticketRepository.findAll(pageable);
        LOGGER.info("Page with tickets is selected ");
        return TicketDtoConverter.convertTicketsPageInTicketsDto(ticketsOnPage);
    }

    @Override
    public List<TicketDto> getAllTicketsByUser(int userId) {
        List<TicketDto> ticketDtoList = ticketRepository.findAllByUserId(userId);
        LOGGER.info("TicketService get all tickets by user with id " + userId);
        return ticketDtoList;
    }

    @Override
    public void addNewTickets(RegisteredUserDto registeredUserDto, PositionDto positionDto) {
        RegisteredUser registeredUser = UserDtoConverter.convertUserDtoInRegisteredUser(registeredUserDto);
        List<Ticket> newTickets = positionDto.getPlaces().stream()
                .map(p -> Ticket.builder()
                        .registeredUser(registeredUser)
                        .schedule(positionDto.getSchedule())
                        .placeNumber(p.getPlace())
                        .placeRow(p.getRow())
                        .build())
                .collect(Collectors.toList());

        LOGGER.info("TicketService prepare Ticket list for saving in database");
        ticketRepository.saveAll(newTickets);
    }
}
