package org.study.cinema.unit.utils;

import org.junit.Test;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;
import org.study.cinema.utils.UserDtoConverter;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDtoConverterTest {

    @Test
    public void shouldConvertRegisteredUserDto() {
        RegisteredUserDto expectedUserDto = createTestRegisteredUserDto();

        RegisteredUserDto resultUserDto = UserDtoConverter.convertUserInRegisteredUserDto(
                createTestRegisteredUser());

        assertThat(resultUserDto, equalTo(expectedUserDto));
    }

    private List<UserRole> createTestUserRole() {
        UserRole userRole = new UserRole();
        userRole.setUserRole("administrator");

        UserRole adminRole = new UserRole();
        adminRole.setUserRole("user");
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);
        userRoles.add(adminRole);
        return userRoles;
    }

    private RegisteredUser createTestRegisteredUser() {
        RegisteredUser registeredUser = new RegisteredUser("Alisa", "Test",
                Gender.FEMALE, "alisa", "a@i.ua", "111");
        registeredUser.setUserRole(createTestUserRole().get(0));
        return registeredUser;
    }

    private RegisteredUserDto createTestRegisteredUserDto() {
        return RegisteredUserDto.builder()
                .userId(0)
                .userName("Alisa")
                .userSurname("Test")
                .gender(Gender.FEMALE)
                .userRole(createTestUserRole().get(0))
                .userLogin("alisa")
                .userEMailAddress("a@i.ua")
                .build();
    }
}
