package org.study.cinema.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;
import org.study.cinema.repositories.UserRepository;
import org.study.cinema.services.impl.UserServiceImpl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldUpdateUser() {
        RegisteredUserDto updateUser = createTestRegisteredUserDto();

        String newUserLogin = "Olyaaa";
        String newPassword = "lala";
        updateUser.setUserLogin(newUserLogin);
        updateUser.setUserPassword(newPassword);

        userService.updateUser(updateUser);

        RegisteredUser resultUser = userRepository.getOne(updateUser.getUserId());

        assertThat(resultUser.getUserLogin(), equalTo(newUserLogin));
        assertThat(resultUser.getUserPassword(), equalTo(newPassword));
    }

    @Test
    public void shouldCreateNewUser() throws Exception {

        RegisteredUserDto newUser = createTestNewRegisteredUserDto();

        userService.createNewUser(newUser);

        RegisteredUser registeredUser = userRepository.findByUserLogin(newUser.getUserLogin());

        assertThat(registeredUser.getUserId(), equalTo(16));
        assertThat(registeredUser.getUserName(), equalTo("Alla"));
        assertThat(registeredUser.getUserSurname(), equalTo("Allova"));
        assertThat(registeredUser.getUserLogin(), equalTo("alla"));
        assertThat(registeredUser.getUserEMailAddress(), equalTo("aa@i.ua"));
    }

    private RegisteredUserDto createTestRegisteredUserDto() {
        return RegisteredUserDto.builder()
                .userId(6)
                .userName("Olga")
                .userSurname("Ospanova")
                .gender(Gender.FEMALE)
                .userRole(createTestUserRole())
                .userLogin("ospOlga")
                .userEMailAddress("o.osp@i.ua")
                .userPassword("111")
                .build();
    }

    private RegisteredUserDto createTestNewRegisteredUserDto() {
        return RegisteredUserDto.builder()
                .userName("Alla")
                .userSurname("Allova")
                .gender(Gender.FEMALE)
                .userRole(createTestUserRole())
                .userLogin("alla")
                .userEMailAddress("aa@i.ua")
                .userPassword("111")
                .build();
    }

    private UserRole createTestUserRole() {
        UserRole userRole = new UserRole();
        userRole.setUserRole("administrator");
        return userRole;
    }

    private UserRole createTestUserRoleNotInDatabase() {
        return UserRole.builder()
                .userRole("manager")
                .build();
    }
}
