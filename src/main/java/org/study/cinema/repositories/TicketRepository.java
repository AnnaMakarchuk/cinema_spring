package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.cinema.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
