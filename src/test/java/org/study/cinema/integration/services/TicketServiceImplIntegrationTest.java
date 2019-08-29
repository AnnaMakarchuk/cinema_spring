package org.study.cinema.integration.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.cinema.CinemaApplicationTests;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.services.impl.TicketServiceImpl;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class TicketServiceImplIntegrationTest extends CinemaApplicationTests {

    @Autowired
    private TicketServiceImpl ticketService;

    @Test
    public void shouldCountPages() {
        int resultPages = ticketService.countPagesQuantity();
        assertThat(resultPages, equalTo(6));
    }

    @Test
    public void shouldGetPagesOfTickets() {
        int page = 2;
        List<TicketDto> resultTicketDtoList = ticketService.allTicketsWithPagination(page);
        assertThat("TicketList size should be 5", resultTicketDtoList, hasSize(5));
        assertThat(resultTicketDtoList.get(0).getTicketId(), equalTo(8));
        assertThat(resultTicketDtoList.get(1).getTicketId(), equalTo(9));
        assertThat(resultTicketDtoList.get(2).getTicketId(), equalTo(10));
        assertThat(resultTicketDtoList.get(3).getTicketId(), equalTo(11));
        assertThat(resultTicketDtoList.get(4).getTicketId(), equalTo(12));
    }

    @Test
    public void shouldGetTicketsByUserId() {
        int userId = 3;
        List<TicketDto> resultTicketDtoList = ticketService.getAllTicketsByUser(userId);

        assertThat(resultTicketDtoList.get(0).getTicketId(), equalTo(1));
        assertThat(resultTicketDtoList.get(2).getTicketId(), equalTo(20));
    }
}
