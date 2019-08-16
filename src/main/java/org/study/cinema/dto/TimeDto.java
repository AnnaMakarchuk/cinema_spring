package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

//this class is needed to save sessionSchedule id when create view for schedule page and join time for similar movies
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TimeDto {

    private int scheduleId;
    private LocalTime time;
}
