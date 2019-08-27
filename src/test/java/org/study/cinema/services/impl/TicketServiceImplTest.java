package org.study.cinema.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.Hall;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.Price;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.Ticket;
import org.study.cinema.entity.enums.WeekDay;
import org.study.cinema.repositories.TicketRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

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

    @Test
    public void shouldGetPageOfTheTickets() {
        List<TicketDto> expectedTicketsDtoList = createTestTicketsDtoList();

        when(ticketRepository.findAll(createPageable())).thenReturn(createTestTicketListPage());
        List<TicketDto> resultTicketsDtoList = ticketService.allTicketsWithPagination(2);

        assertThat(resultTicketsDtoList, equalTo(expectedTicketsDtoList));
    }

    @Test
    public void shouldGetAllTicketsByUser() {
        List<TicketDto> expectedTicketsDtoList = createTestTicketsDtoList();

        when(ticketRepository.findAllByUserId(1)).thenReturn(createTestTicketsDtoList());
        List<TicketDto> resultTicketsDtoList = ticketService.getAllTicketsByUser(1);

        assertThat(resultTicketsDtoList, equalTo(expectedTicketsDtoList));
    }

    private Movie createTestMovie() {
        return Movie.builder()
                .id(0)
                .movieName("Avengers")
                .build();
    }

    private Price createTestPrice() {
        return Price.builder()
                .id(1)
                .row(2)
                .price(50.00)
                .build();
    }

    private Hall createTestHall() {
        return Hall.builder()
                .id(1)
                .maxRow(3)
                .maxPlacesInRow(4)
                .hallName("Gold")
                .prices(Collections.singletonList(createTestPrice()))
                .build();
    }

    private Schedule createTestSchedule() {
        return Schedule.builder()
                .id(1)
                .hall(createTestHall())
                .weekDay(WeekDay.MONDAY)
                .movie(createTestMovie())
                .time(LocalTime.of(9, 0))
                .build();
    }

    private Pageable createPageable() {
        return PageRequest.of(1, 5, Sort.by(
                Sort.Order.asc("id")));
    }

    private Page<Ticket> createTestTicketListPage() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(Ticket.builder()
                .id(1)
                .schedule(createTestSchedule())
                .placeNumber(1)
                .placeRow(2)
                .ticketPrice(50.00)
                .build());
        ticketList.add(Ticket.builder()
                .id(2)
                .schedule(createTestSchedule())
                .placeNumber(2)
                .placeRow(2)
                .ticketPrice(50.00)
                .build());
        return new PageImpl<Ticket>(ticketList);
    }

    private List<Ticket> createTestTicketList() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(Ticket.builder()
                .id(1)
                .schedule(createTestSchedule())
                .placeNumber(1)
                .placeRow(2)
                .ticketPrice(50.00)
                .build());
        ticketList.add(Ticket.builder()
                .id(2)
                .schedule(createTestSchedule())
                .placeNumber(2)
                .placeRow(2)
                .ticketPrice(50.00)
                .build());
        return ticketList;
    }

    private List<TicketDto> createTestTicketsDtoList() {
        List<TicketDto> ticketDtoList = new ArrayList<>();
        ticketDtoList.add(TicketDto.builder().
                ticketId(1)
                .hallName(createTestHall().getHallName())
                .movieName(createTestMovie().getMovieName())
                .placeNumber(1)
                .placeRow(2)
                .scheduleTime(createTestSchedule().getTime())
                .weekDay(createTestSchedule().getWeekDay())
                .ticketPrice(50.00)
                .build());
        ticketDtoList.add(TicketDto.builder().
                ticketId(2)
                .hallName(createTestHall().getHallName())
                .movieName(createTestMovie().getMovieName())
                .placeNumber(2)
                .placeRow(2)
                .scheduleTime(createTestSchedule().getTime())
                .weekDay(createTestSchedule().getWeekDay())
                .ticketPrice(50.00)
                .build());
        return ticketDtoList;
    }
}
