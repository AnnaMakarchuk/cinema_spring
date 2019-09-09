package org.study.cinema.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.study.cinema.dto.PositionDto;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;
import org.study.cinema.exceptions.DataNotFound;
import org.study.cinema.services.TicketService;

import java.util.Objects;

@RestController
public class TicketRestController {

    private static final Logger LOGGER = LogManager.getLogger(TicketRestController.class);

    @Autowired
    private TicketService ticketService;

    // TODO add user Id after security
    @RequestMapping(value = "/client/boughttickets", method = RequestMethod.POST,
            headers = "Accept=application/x-www-form-urlencoded")
    @ResponseStatus(HttpStatus.OK)
    public void boughtTickets(@RequestBody PositionDto positionDto) {

        UserRole userRole = UserRole.builder()
                .userRole("client")
                .build();
        RegisteredUserDto user = RegisteredUserDto.builder()
                .userId(4)
                .userName("Ivan")
                .userSurname("Ivanov")
                .gender(Gender.MALE)
                .userRole(userRole)
                .userLogin("IIva")
                .userEMailAddress("ivanov@gmail.com")
                .build();

        LOGGER.info("Position's is" + positionDto.toString());

        if (Objects.isNull(positionDto.getPlaces()) || Objects.isNull(positionDto.getScheduleId())) {
            LOGGER.error("Position's data is null");
            throw new DataNotFound("Data not found");
        }
        try {
            ticketService.addNewTickets(user, positionDto);
        } catch (Exception e) {
            LOGGER.error("Price in hall not found in database", e);
            throw new DataNotFound("Data not found in database");
        }
        LOGGER.info("New tickets was added");
    }
}
