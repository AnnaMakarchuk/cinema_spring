package org.study.cinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.Ticket;
import org.study.cinema.repositories.TicketRepository;
import org.study.cinema.services.TicketService;
import org.study.cinema.utils.TicketDtoConverter;

import java.util.List;
import java.util.Optional;
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
        List<Optional<TicketDto>> optionalTicketsList = ticketRepository.findAllByUserId(userId);
        LOGGER.info("TicketService get all tickets by user with id " + userId);
        return optionalTicketsList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
