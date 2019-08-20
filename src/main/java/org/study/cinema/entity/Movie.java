package org.study.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String movieName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "duration")
    private int movieDuration;

    @Column(name = "age_limit")
    private int ageLimit;

    @Column(name = "description")
    private String movieDescription;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany (mappedBy = "movie",  fetch = FetchType.EAGER)
    private List<Schedule> schedules;
}
