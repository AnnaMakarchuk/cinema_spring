package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.cinema.entity.Hall;

public interface HallRepository extends JpaRepository<Hall, Integer> {

}
