package org.study.cinema.integration.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.study.cinema.entity.UserRole;
import org.study.cinema.repositories.UserRoleRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRoleRepositoryIntegrationTest {

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
