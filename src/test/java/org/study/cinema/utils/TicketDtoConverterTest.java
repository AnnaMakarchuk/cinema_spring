package org.study.cinema.utils;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.Hall;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.Price;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.Ticket;
import org.study.cinema.entity.enums.WeekDay;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TicketDtoConverterTest {

    @Test
    public void shouldConvertTicketPageInTicketDto() {
        List<TicketDto> expectedTicketsDtoList = createTestTicketsDtoList();

        List<TicketDto> resultTicketsDtoList = TicketDtoConverter
                .convertTicketsPageInTicketsDto(createTestTicketList());

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

    private Page<Ticket> createTestTicketList() {
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
