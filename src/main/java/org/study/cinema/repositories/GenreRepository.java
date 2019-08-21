package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.study.cinema.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query("Select id from Genre Where genre = :genre")
    int findByGenre (@Param("genre") String genre);
}
