package org.study.cinema.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.study.cinema.repositories.TicketRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;


    @Before
    public void setUp() {


    }

    @Test
    public void shouldCalculatePagesForNotEmptyTickets() {
        when(ticketRepository.count()).thenReturn(10L);
        int resultQuantity = ticketService.countPagesQuantity();

        assertThat(resultQuantity, is((2)));
    }

    @Test
    public void shouldCalculatePagesForEmptyTickets() {
        when(ticketRepository.count()).thenReturn(0L);
        int resultQuantity = ticketService.countPagesQuantity();

        assertThat(resultQuantity, is((0)));
    }

}