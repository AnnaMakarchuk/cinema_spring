package org.study.cinema.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.study.cinema.dto.HallDto;
import org.study.cinema.dto.PlaceDto;
import org.study.cinema.entity.Schedule;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HallDtoConverter {

    private static final Logger LOGGER = LogManager.getLogger(HallDtoConverter.class);

    public static HallDto convertHallDtoWithOccupiedPlacesAndPriceFromSchedule(Schedule schedule) {
        HallDto hallDto = HallDto.builder()
                .hallId(schedule.getHall().getId())
                .hallName(schedule.getHall().getHallName())
                .maxPlacesInRow(schedule.getHall().getMaxPlacesInRow())
                .maxRow(schedule.getHall().getMaxRow())
                .prices(schedule.getHall().getPrices())
                .occupiedPlaces(getOccupiedPlaceDtoList(schedule))
                .build();
        LOGGER.info("Hall Dto was converted from schedule with id " + schedule.getId());
        return hallDto;
    }

    private static List<PlaceDto> getOccupiedPlaceDtoList(Schedule schedule) {
        if (Objects.isNull(schedule.getTicketsList())) {
            return Collections.emptyList();
        }
        return schedule.getTicketsList().stream()
                .map(ticket -> PlaceDto.builder()
                        .row(ticket.getPlaceRow())
                        .place(ticket.getPlaceNumber())
                        .build())
                .collect(Collectors.toList());
    }
}
