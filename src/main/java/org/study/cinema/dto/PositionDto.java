package org.study.cinema.dto;

import lombok.*;
import org.study.cinema.entity.Schedule;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PositionDto {

    private Schedule schedule;
    private List<PlaceDto> places;
}
