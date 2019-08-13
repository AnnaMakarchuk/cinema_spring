package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.study.cinema.entity.MovieGenre;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MovieDto {

    private int movieId;
    private String movieName;
    private MovieGenre movieGenre;
    private int movieDuration;
    private int ageLimit;
    private String movieDescription;
}
