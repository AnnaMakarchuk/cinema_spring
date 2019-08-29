package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.ToString;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.Schedule;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@XmlRootElement

public class MovieDto {

    private int movieId;
    private String movieName;
    private String movieGenre;
    private int movieDuration;
    private int ageLimit;
    private String movieDescription;

    private List<Schedule> scheduleList;
    private Set<RegisteredUser> registeredUsers;

}
