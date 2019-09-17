package org.study.cinema.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.dto.PlaceDto;
import org.study.cinema.dto.PositionDto;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;
import org.study.cinema.services.impl.TicketServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class TicketServiceImplIntegrationTest {

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

    @Test
    public void shouldAddTicketsInDatabase() {
        ticketService.addNewTickets(createTestRegisteredUserDto(), createTestPositionDto());
        List<TicketDto> tickets = ticketService.getAllTicketsByUser(4);
        assertThat(tickets, hasSize(9));
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
                .scheduleId(5)
                .places(placeDtos)
                .build();
    }


}
