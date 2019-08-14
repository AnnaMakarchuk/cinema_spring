package org.study.cinema.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.study.cinema.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    int findByGenre (String genre);
}
