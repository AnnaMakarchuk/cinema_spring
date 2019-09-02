package org.study.cinema.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.study.cinema.entity.enums.Gender;

@Entity
@Table(name = "user",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"login", "email_address"})})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role_id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "name")
    private String userName;

    @Column(name = "surname")
    private String userSurname;

    @Column(name = "gender", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role")
    private UserRole userRole;

    public User(String userName, String userSurname, Gender gender) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.gender = gender;
    }
}

