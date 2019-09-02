package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.study.cinema.entity.RegisteredUser;

public interface UserRepository extends JpaRepository<RegisteredUser, Integer> {

    RegisteredUser findByUserLogin(String userLogin);

    @Modifying
    @Query("Update RegisteredUser u SET u.userLogin = ?1, u.userPassword = ?2 Where u.id = ?3")
    void updateUser(String userLogin, String userPassword, int userId);
}
