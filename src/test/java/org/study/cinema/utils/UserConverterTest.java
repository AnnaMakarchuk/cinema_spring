package org.study.cinema.utils;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.study.cinema.dto.AdministratorDto;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.Administrator;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;

public class UserConverterTest {

    private UserDtoConverter userConverter;

    private RegisteredUser firstUser;
    private RegisteredUser secondUser;
    private Administrator administrator;

    private RegisteredUserDto expectedFirstUser;
    private RegisteredUserDto expectedSecondUser;
    private AdministratorDto expectedAdministrator;

    @Before
    public void setUp() {
        userConverter = new UserDtoConverter();

        UserRole userRole = new UserRole();
        userRole.setUserRole("user");

        firstUser = new RegisteredUser(1, "Alisa", "Test",
                Gender.FEMALE, userRole, "alisa", "a@i.ua", "111");
        secondUser = new RegisteredUser(1, "Kolya", "Test",
                Gender.MALE, userRole, "Kolya", "a@i.ua", "111");

        UserRole admin = new UserRole();
        userRole.setUserRole("administrator");

        administrator = new Administrator(1, "Cate", "Test",
                Gender.FEMALE, admin, "cat", "a@i.ua",
                "111", 25.00, 40);

        expectedFirstUser = RegisteredUserDto.builder()
                .userId(1)
                .userName("Alisa")
                .userSurname("Test")
                .gender(Gender.FEMALE)
                .userRole(userRole)
                .userLogin("alisa")
                .userEMailAddress("a@i.ua")
                .build();

        expectedSecondUser = RegisteredUserDto.builder()
                .userId(1)
                .userName("Kolya")
                .userSurname("Test")
                .gender(Gender.MALE)
                .userRole(userRole)
                .userLogin("Kolya")
                .userEMailAddress("a@i.ua")
                .build();

        expectedAdministrator = AdministratorDto.builder()
                .administratorId(1)
                .administratorName("Cate")
                .administratorSurname("Test")
                .gender(Gender.FEMALE)
                .userRole(admin)
                .administratorLogin("cat")
                .administratorEMailAddress("a@i.ua")
                .administratorWorkingHoursPerWeek(40)
                .build();
    }

    @Test
    public void shouldConvertRegisteredUserDto() {
        RegisteredUserDto expectedUser = expectedFirstUser;

        RegisteredUserDto resultUser = userConverter.convertUserInRegisteredUserDto(firstUser);

        assertThat(resultUser, equalTo(expectedUser));
    }

    @Test
    public void shouldConvertListOfRegisteredUserDto() {
        List<RegisteredUserDto> expectedUserList = Arrays.asList(expectedFirstUser, expectedSecondUser);


        List<RegisteredUserDto> resultUserList = userConverter.convertUserListInRegisteredUserDtoList
                (Arrays.asList(firstUser, secondUser));

        assertThat(resultUserList, equalTo(expectedUserList));
    }

    @Test
    public void shouldConvertAdministratorDto() {
        AdministratorDto expectedAdmin = expectedAdministrator;

        AdministratorDto resultAdministrator = userConverter.convertUserInAdministratorDto(administrator);

        assertThat(resultAdministrator, equalTo(expectedAdmin));
    }
}
