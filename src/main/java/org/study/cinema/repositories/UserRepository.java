package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.cinema.entity.RegisteredUser;

public interface UserRepository extends JpaRepository<RegisteredUser, Integer> {
    RegisteredUser findByUserLogin(String userLogin);
}
