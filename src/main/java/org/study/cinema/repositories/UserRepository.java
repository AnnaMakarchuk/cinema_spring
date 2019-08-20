package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.study.cinema.entity.RegisteredUser;

import java.util.List;

public interface UserRepository extends JpaRepository<RegisteredUser, Integer> {
    RegisteredUser findByUserLogin(String userLogin);

}
