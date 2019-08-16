package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.cinema.entity.Movie;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Optional<Movie>> findByIsActive(boolean isActive);

}
