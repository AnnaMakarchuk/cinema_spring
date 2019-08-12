package org.study.cinema.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.study.cinema.entity.enums.Gender;

@Entity
@DiscriminatorValue("2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegisteredUser extends User {

    @Column(name = "login")
    private String userLogin;

    @Column(name = "email_address")
    private String userEMailAddress;

    @Column(name = "password")
    private String userPassword;

    public RegisteredUser(int userId, String userName, String userSurname, Gender gender, UserRole userRole,
                          String userLogin, String userEMailAddress, String userPassword) {
        super(userId, userName, userSurname, gender, userRole);
        this.userLogin = userLogin;
        this.userEMailAddress = userEMailAddress;
        this.userPassword = userPassword;
    }
}
