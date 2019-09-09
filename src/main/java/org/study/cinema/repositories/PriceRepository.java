package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.cinema.entity.Price;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

    Optional<Price> findAllByRowAndHallId(int row, int hallId);
}
