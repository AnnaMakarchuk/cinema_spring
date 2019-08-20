package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.study.cinema.entity.Movie;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByIsActive(boolean isActive);

    @Modifying
    @Query("Update Movie m SET m.isActive = false WHERE m.id = :movieId")
    void updateByMovieId(@Param("movieId") int movieId);
}
