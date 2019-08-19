package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query ("SELECT new org.study.cinema.dto.TicketDto (t.id, t.placeRow, t.placeNumber, t.ticketPrice, u.id, " +
            "s.weekDay, s.time, h.hallName, m.movieName) FROM Ticket t " +
            "JOIN FETCH t.user u " +
            "JOIN FETCH t.schedule s " +
            "JOIN FETCH s.hall h " +
            "JOIN FETCH s.movie m " +
            "WHERE u.id = :userId")
    List<Optional<TicketDto>> findAllByUserId(@Param("userId") int userId);
}
