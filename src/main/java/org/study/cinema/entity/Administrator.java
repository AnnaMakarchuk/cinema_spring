package org.study.cinema.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("1")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Administrator extends RegisteredUser {

    @Column(name = "salary")
    private double monthSalary;

    @Column(name = "working_hours_week")
    private int workingHoursWeek;
}
