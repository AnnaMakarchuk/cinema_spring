package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.study.cinema.entity.Price;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HallDto {

    private int hallId;
    private String hallName;
    private int maxRow;
    private int maxPlacesInRow;
    private List<Price> prices;
    private List<PlaceDto> occupiedPlaces;
}
