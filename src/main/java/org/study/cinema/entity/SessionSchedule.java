package org.study.cinema.entity;

import java.time.LocalTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;
import org.study.cinema.entity.enums.WeekDay;

@Entity
@Table(name = "session_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SessionSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int scheduleId;

    @Column(name = "day_of_week", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private WeekDay weekDay;

    @Column(name = "time")
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @OneToMany(mappedBy = "sessionSchedule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> ticketsList;
}
