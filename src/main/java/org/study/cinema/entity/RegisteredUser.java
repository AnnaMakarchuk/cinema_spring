package org.study.cinema.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.study.cinema.entity.enums.Gender;

import java.util.List;

@Entity
@DiscriminatorValue("2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisteredUser extends User {

    @Column(name = "login")
    private String userLogin;

    @Column(name = "email_address")
    private String userEMailAddress;

    @Column(name = "password")
    private String userPassword;

    @OneToMany(mappedBy = "registeredUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> ticketList;

    public RegisteredUser(String userName, String userSurname, Gender gender,
                          String userLogin, String userEMailAddress, String userPassword) {
        super(userName, userSurname, gender);
        this.userLogin = userLogin;
        this.userEMailAddress = userEMailAddress;
        this.userPassword = userPassword;
    }

    public RegisteredUser(String userName, String userSurname, Gender gender,
                          String userLogin, String userEMailAddress) {
        super(userName, userSurname, gender);
        this.userLogin = userLogin;
        this.userEMailAddress = userEMailAddress;
    }
}
