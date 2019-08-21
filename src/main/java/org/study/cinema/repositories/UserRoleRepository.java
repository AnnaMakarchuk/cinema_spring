package org.study.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.cinema.entity.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    Optional<UserRole> findByUserRole(String userRole);
}
