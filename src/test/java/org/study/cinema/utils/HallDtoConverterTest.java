package org.study.cinema.utils;

import org.junit.Before;
import org.junit.Test;
import org.study.cinema.dto.HallDto;
import org.study.cinema.dto.PlaceDto;
import org.study.cinema.entity.Genre;
import org.study.cinema.entity.Hall;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.Price;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.Ticket;
import org.study.cinema.entity.enums.WeekDay;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HallDtoConverterTest {

    private Schedule scheduleWithoutTickets;
    private Schedule scheduleWithTickets;

    private HallDto hallDtoWithoutTickets;
    private HallDto hallDtoWithTickets;

    @Before
    public void setUp() {
        Movie firstMovie = Movie.builder()
                .id(0)
                .movieName("Avengers")
                .build();
        Price price = Price.builder()
                .id(1)
                .row(2)
                .price(50.00)
                .build();
        Hall hall = Hall.builder()
                .id(1)
                .maxRow(3)
                .maxPlacesInRow(4)
                .hallName("Gold")
                .prices(Collections.singletonList(price))
                .build();
        Ticket firstTicket = Ticket.builder()
                .placeNumber(1)
                .placeRow(2)
                .build();
        Ticket secondTicket = Ticket.builder()
                .placeNumber(2)
                .placeRow(2)
                .build();
        scheduleWithoutTickets = Schedule.builder()
                .id(1)
                .hall(hall)
                .weekDay(WeekDay.MONDAY)
                .movie(firstMovie)
                .time(LocalTime.of(9, 0))
                .build();
        scheduleWithTickets = Schedule.builder()
                .id(1)
                .hall(hall)
                .weekDay(WeekDay.MONDAY)
                .movie(firstMovie)
                .ticketsList(Arrays.asList(firstTicket, secondTicket))
                .time(LocalTime.of(9, 0))
                .build();
        hallDtoWithoutTickets = HallDto.builder()
                .hallId(1)
                .maxRow(3)
                .maxPlacesInRow(4)
                .hallName("Gold")
                .occupiedPlaces(Collections.emptyList())
                .prices(Collections.singletonList(price))
                .build();
        hallDtoWithTickets = HallDto.builder()
                .hallId(1)
                .maxRow(3)
                .maxPlacesInRow(4)
                .hallName("Gold")
                .occupiedPlaces(Arrays.asList(new PlaceDto(2, 1), new PlaceDto(2, 2)))
                .prices(Collections.singletonList(price))
                .build();
    }

    @Test
    public void shouldConvertHallDtoFromScheduleWithoutTickets() {
        HallDto expectedHallDto = hallDtoWithoutTickets;

        HallDto resultHallDto = HallDtoConverter
                .convertHallDtoWithOccupiedPlacesAndPriceFromSchedule(scheduleWithoutTickets);

        assertThat(resultHallDto, equalTo(expectedHallDto));
    }

    @Test
    public void shouldConvertHallDtoFromScheduleWithTickets() {
        HallDto expectedHallDto = hallDtoWithTickets;

        HallDto resultHallDto = HallDtoConverter
                .convertHallDtoWithOccupiedPlacesAndPriceFromSchedule(scheduleWithTickets);

        assertThat(resultHallDto, equalTo(expectedHallDto));
    }
}
