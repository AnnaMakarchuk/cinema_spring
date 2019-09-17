package org.study.cinema.integration.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;
import org.study.cinema.repositories.UserRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldReturnUserByLogin() {
        RegisteredUser expectedUser = createTestUser();

        String userLogin = "ospOlga";
        RegisteredUser resultUser = userRepository.findByUserLogin(userLogin);

        assertThat(resultUser.getUserName(), equalTo(expectedUser.getUserName()));
        assertThat(resultUser.getUserSurname(), equalTo(expectedUser.getUserSurname()));
        assertThat(resultUser.getUserId(), equalTo(6));
    }

    @Test
    public void shouldUpdateUserByLoginAndPassword() {
        RegisteredUser expectedUser = createTestUser();
        expectedUser.setUserRole(createTestUserRole());

        int userId = 6;
        String newUserLogin = "ospOlga";
        String newPassword = "222";

        userRepository.updateUser(newUserLogin, newPassword, userId);

        RegisteredUser resultUser = userRepository.getOne(userId);

        assertThat(resultUser.getUserLogin(), equalTo(newUserLogin));
        assertThat(resultUser.getUserPassword(), equalTo(newPassword));
    }

    private RegisteredUser createTestUser() {
        return new RegisteredUser("Olga", "Ospanova",
                Gender.FEMALE, "ospOlga", "o.osp@i.ua", "111");
    }

    private UserRole createTestUserRole() {
        return UserRole.builder()
                .userRole("client")
                .build();
    }
}
