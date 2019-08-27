package org.study.cinema.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.RegisteredUser;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;
import org.study.cinema.repositories.UserRepository;
import org.study.cinema.repositories.UserRoleRepository;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldUpdateUserInDatabase() {
        userService.updateUser(createTestRegisteredUserDto());
        verify(userRepository).updateUser("alisa", "111");
    }

    @Test
    public void shouldCreateNewUserInDatabase() throws Exception {
        when(userRoleRepository.findByUserRole("client")).thenReturn(Optional.of(createTestUserRole()));
        userService.createNewUser(createTestRegisteredUserDto());

        verify(userRepository).save(createTestRegisteredUser());
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionIfRoleNotFound() throws Exception {
        when(userRoleRepository.findByUserRole("client")).thenReturn(Optional.empty());
        userService.createNewUser(createTestRegisteredUserDto());
    }

    private RegisteredUserDto createTestRegisteredUserDto() {
        return RegisteredUserDto.builder()
                .userId(0)
                .userName("Alisa")
                .userSurname("Test")
                .gender(Gender.FEMALE)
                .userLogin("alisa")
                .userEMailAddress("a@i.ua")
                .userPassword("111")
                .build();
    }

    private RegisteredUser createTestRegisteredUser() {
        RegisteredUser registeredUser = new RegisteredUser("Alisa", "Test",
                Gender.FEMALE, "alisa", "a@i.ua", "111");
        registeredUser.setUserRole(createTestUserRole());
        return registeredUser;
    }

    private UserRole createTestUserRole() {
        return UserRole.builder()
                .userRole("client")
                .build();
    }
}
