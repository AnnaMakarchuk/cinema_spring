package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.study.cinema.entity.Price;
import org.study.cinema.entity.Schedule;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class HallDto {

    private int hallId;
    private String hallName;
    private int maxRow;
    private int maxPlacesInRow;
    private ScheduleDto schedule;
    private List<Price> prices;
    private List<PlaceDto> occupiedPlaces;
}
