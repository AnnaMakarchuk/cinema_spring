package org.study.cinema.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.study.cinema.dto.AdministratorDto;
import org.study.cinema.dto.RegisteredUserDto;
import org.study.cinema.entity.Administrator;
import org.study.cinema.entity.RegisteredUser;

public class UserConverter {

    public RegisteredUserDto convertUserInRegisteredUserDto(RegisteredUser registeredUser) {
        return userConverter(registeredUser);
    }

    public List<RegisteredUserDto> convertUserListInRegisteredUserDtoList(List<RegisteredUser> registeredUser) {
        return registeredUser.stream()
                .map(this::userConverter)
                .collect(Collectors.toList());
    }

    public AdministratorDto convertUserInAdministratorDto(Administrator administrator) {
        return AdministratorDto.builder()
                .administratorId(administrator.getUserId())
                .administratorName(administrator.getUserName())
                .administratorSurname(administrator.getUserSurname())
                .gender(administrator.getGender())
                .userRole(administrator.getUserRole())
                .administratorLogin(administrator.getUserLogin())
                .administratorEMailAddress(administrator.getUserEMailAddress())
                .administratorWorkingHoursPerWeek(administrator.getWorkingHoursWeek())
                .build();
    }

    private RegisteredUserDto userConverter(RegisteredUser user) {
        return RegisteredUserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userSurname(user.getUserSurname())
                .gender(user.getGender())
                .userRole(user.getUserRole())
                .userLogin(user.getUserLogin())
                .userEMailAddress(user.getUserEMailAddress())
                .build();
    }
}
