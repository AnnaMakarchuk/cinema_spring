package org.study.cinema.utils;

import org.junit.Test;
import org.study.cinema.dto.HallDto;
import org.study.cinema.dto.PlaceDto;
import org.study.cinema.entity.Hall;
import org.study.cinema.entity.Movie;
import org.study.cinema.entity.Price;
import org.study.cinema.entity.Schedule;
import org.study.cinema.entity.Ticket;
import org.study.cinema.entity.enums.WeekDay;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HallDtoConverterTest {

    @Test
    public void shouldConvertHallDtoFromScheduleWithoutTickets() {
        HallDto expectedHallDto = createTestHallDtoWithoutTickets();

        HallDto resultHallDto = HallDtoConverter
                .convertHallDtoWithOccupiedPlacesAndPriceFromSchedule(createTestScheduleWithoutTickets());

        assertThat(resultHallDto, equalTo(expectedHallDto));
    }

    @Test
    public void shouldConvertHallDtoFromScheduleWithTickets() {
        HallDto expectedHallDto = createTestHallDtoWitTickets();

        HallDto resultHallDto = HallDtoConverter
                .convertHallDtoWithOccupiedPlacesAndPriceFromSchedule(createTestScheduleWithTickets());

        assertThat(resultHallDto, equalTo(expectedHallDto));
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

    private List<Ticket> createTestTicketList() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(Ticket.builder()
                .placeNumber(1)
                .placeRow(2)
                .build());
        ticketList.add(Ticket.builder()
                .placeNumber(2)
                .placeRow(2)
                .build());
        return ticketList;
    }

    private Schedule createTestScheduleWithoutTickets() {
        return Schedule.builder()
                .id(1)
                .hall(createTestHall())
                .weekDay(WeekDay.MONDAY)
                .movie(createTestMovie())
                .ticketsList(Collections.emptyList())
                .time(LocalTime.of(9, 0))
                .build();
    }

    private Schedule createTestScheduleWithTickets() {
        return Schedule.builder()
                .id(1)
                .hall(createTestHall())
                .weekDay(WeekDay.MONDAY)
                .movie(createTestMovie())
                .ticketsList(createTestTicketList())
                .time(LocalTime.of(9, 0))
                .build();
    }

    private HallDto createTestHallDtoWithoutTickets() {
        return HallDto.builder()
                .hallId(1)
                .maxRow(3)
                .maxPlacesInRow(4)
                .hallName("Gold")
                .occupiedPlaces(Collections.emptyList())
                .prices(Collections.singletonList(createTestPrice()))
                .build();
    }

    private HallDto createTestHallDtoWitTickets() {
        return HallDto.builder()
                .hallId(1)
                .maxRow(3)
                .maxPlacesInRow(4)
                .hallName("Gold")
                .occupiedPlaces(Arrays.asList(new PlaceDto(2, 1), new PlaceDto(2, 2)))
                .prices(Collections.singletonList(createTestPrice()))
                .build();
    }
}
