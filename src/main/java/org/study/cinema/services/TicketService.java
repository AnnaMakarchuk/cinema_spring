package org.study.cinema.services;

import org.springframework.data.domain.Page;
import org.study.cinema.dto.TicketDto;

import java.util.List;

public interface TicketService {

    int countPagesQuantity();

    Page<TicketDto> allTicketsWithPagination(int page);
}
