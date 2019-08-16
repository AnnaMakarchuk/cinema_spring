package org.study.cinema.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.Ticket;
import org.study.cinema.repositories.TicketRepository;
import org.study.cinema.services.TicketService;
import org.study.cinema.utils.TicketDtoConverter;

import java.util.Objects;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger LOGGER = LogManager.getLogger(TicketServiceImpl.class);
    private static final int ROW_ON_PAGE = 5;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public int countPagesQuantity() {
        long ticketCount = ticketRepository.count();
        if (Objects.isNull(ticketCount)) {
            return (int) 0L;
        }
        return (int) (ticketCount / ROW_ON_PAGE);
    }

    @Override
    public Page<TicketDto> allTicketsWithPagination(int page) {
        Pageable pageable = PageRequest.of(page, ROW_ON_PAGE);
        Page<Ticket> ticketsOnPage = ticketRepository.findAll(pageable);
        LOGGER.info("Page with tickets is selected ");

        return TicketDtoConverter.convertTicketsPageInTicketsDto(ticketsOnPage);
    }
}
