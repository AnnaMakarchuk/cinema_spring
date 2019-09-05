package org.study.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.study.cinema.entity.UserRole;
import org.study.cinema.entity.enums.Gender;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisteredUserDto {

    private int userId;
    private String userName;
    private String userSurname;
    private Gender gender;
    private UserRole userRole;
    private String userLogin;
    private String userEMailAddress;
    private String userPassword;

    public RegisteredUserDto(int userId, String userLogin, String userPassword) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }
}
