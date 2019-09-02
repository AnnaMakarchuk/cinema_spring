package org.study.cinema.integration.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.CinemaApplicationTests;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.Ticket;
import org.study.cinema.repositories.TicketRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@Transactional
public class TicketRepositoryIntegrationTest extends CinemaApplicationTests {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void shouldGetAllTicketsByUserId() {
        int userId = 3;
        List<TicketDto> resultTicketsList = ticketRepository.findAllByUserId(userId);

        assertThat("TicketList for user with id 3 size should be 10", resultTicketsList, hasSize(10));
    }

    @Test
    public void shouldGetAllTicketsByScheduleId() {
        int scheduleId = 34;
        List<Ticket> resultTicketsList = ticketRepository.findAllByScheduleId(scheduleId);

        assertThat("TicketList for schedule with id 34 size should be 4", resultTicketsList, hasSize(4));
    }
}
