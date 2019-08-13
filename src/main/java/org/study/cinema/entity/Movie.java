package org.study.cinema.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int movieId;

    @Column(name = "name")
    private String movieName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private MovieGenre movieGenre;

    @Column(name = "duration")
    private int movieDuration;

    @Column(name = "ageLimit")
    private int ageLimit;

    @Column(name = "description")
    private String movieDescription;

    @OneToOne(mappedBy = "movie")
    private SessionSchedule sessionSchedule;
}
