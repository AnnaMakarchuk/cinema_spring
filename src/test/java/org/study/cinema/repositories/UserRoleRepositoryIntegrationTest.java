package org.study.cinema.repositories;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.cinema.CinemaApplicationTests;
import org.study.cinema.entity.UserRole;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserRoleRepositoryIntegrationTest extends CinemaApplicationTests {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void shouldGetUserRoleByUserRoleName() {
        UserRole expectedUserRole = UserRole.builder()
                .id(2)
                .userRole("client")
                .build();

        String userRole = "client";
        UserRole resultUserRole = userRoleRepository.findByUserRole(userRole).get();
        System.out.println(resultUserRole.toString());

        assertThat(resultUserRole.getId(), equalTo(expectedUserRole.getId()));
        assertThat(resultUserRole.getUserRole(), equalTo(expectedUserRole.getUserRole()));
    }
}
