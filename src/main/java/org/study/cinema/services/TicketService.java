package org.study.cinema.services;

import org.study.cinema.dto.PositionDto;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.dto.TicketDto;

import java.util.List;

public interface TicketService {

    int countPagesQuantity();

    List<TicketDto> allTicketsWithPagination(int page);

    List<TicketDto> getAllTicketsByUser(int userId);

    void addNewTickets(RegisteredUserDto registeredUserDto, PositionDto positionDto) throws Exception;
}
