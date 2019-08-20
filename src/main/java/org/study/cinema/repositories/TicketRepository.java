package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.study.cinema.dto.TicketDto;
import org.study.cinema.entity.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT new org.study.cinema.dto.TicketDto (t.id, t.placeRow, t.placeNumber, t.ticketPrice, u.id, " +
            "s.weekDay, s.time, h.hallName, m.movieName) FROM Ticket t " +
            "JOIN t.registeredUser u " +
            "JOIN t.schedule s " +
            "JOIN s.hall h " +
            "JOIN s.movie m " +
            "WHERE u.id = :userId")
    List<TicketDto> findAllByUserId(@Param("userId") int userId);

    List<Ticket> findAllByScheduleId(int scheduleId);
}
