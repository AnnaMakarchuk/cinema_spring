package org.study.cinema.unit.services.impl;

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
import org.study.cinema.dto.PlaceDto;
import org.study.cinema.dto.PositionDto;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.*;
import org.study.cinema.entity.enums.Gender;
import org.study.cinema.entity.enums.WeekDay;
import org.study.cinema.repositories.PriceRepository;
import org.study.cinema.repositories.ScheduleRepository;
import org.study.cinema.repositories.TicketRepository;
import org.study.cinema.services.impl.TicketServiceImpl;

import java.time.LocalTime;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

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

    @Test
    public void shouldAddNewTickets() {
        when(scheduleRepository.getOne(1)).thenReturn(createTestSchedule());
        when(priceRepository.findAllByRowAndHallId(1, 1))
                .thenReturn(Optional.ofNullable(createTestPriceList().get(0)));
        when(priceRepository.findAllByRowAndHallId(4, 1))
                .thenReturn(Optional.ofNullable(createTestPriceList().get(1)));
        when(priceRepository.findAllByRowAndHallId(6, 1))
                .thenReturn(Optional.ofNullable(createTestPriceList().get(2)));

        ticketService.addNewTickets(createTestRegisteredUserDto(), createTestPositionDto());

        verify(scheduleRepository).getOne(1);
        verify(priceRepository).findAllByRowAndHallId(1, 1);
        verify(priceRepository).findAllByRowAndHallId(4, 1);
        verify(priceRepository).findAllByRowAndHallId(6, 1);
        verify(ticketRepository).saveAll(anyIterable());
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

    private List<Price> createTestPriceList() {
        List<Price> prices = new ArrayList<>();
        prices.add(Price.builder()
                .row(1)
                .price(50.00)
                .build());
        prices.add(Price.builder()
                .row(4)
                .price(75.00)
                .build());
        prices.add(Price.builder()
                .row(6)
                .price(150.00)
                .build());
        return prices;
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

    private RegisteredUserDto createTestRegisteredUserDto() {
        UserRole userRole = UserRole.builder()
                .userRole("client")
                .build();
        return RegisteredUserDto.builder()
                .userId(4)
                .userName("Ivan")
                .userSurname("Ivanov")
                .gender(Gender.MALE)
                .userRole(userRole)
                .userLogin("IIva")
                .userEMailAddress("ivanov@gmail.com")
                .build();
    }

    private PositionDto createTestPositionDto() {
        List<PlaceDto> placeDtos = Arrays.asList
                (new PlaceDto(1, 3), new PlaceDto(4, 6), new PlaceDto(6, 1));
        return PositionDto.builder()
                .scheduleId(1)
                .places(placeDtos)
                .build();
    }
}
