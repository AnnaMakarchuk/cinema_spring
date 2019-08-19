package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.ToString;
import org.study.cinema.entity.Genre;

import javax.xml.bind.annotation.XmlRootElement;

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

    public MovieDto(String movieName, String movieGenre, int movieDuration, int ageLimit, String movieDescription) {
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.movieDuration = movieDuration;
        this.ageLimit = ageLimit;
        this.movieDescription = movieDescription;
    }
}
