package com.crud.diver.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "LOCALIZATION")
    private String localization;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "LOGIN")
    private String login;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(
            targetEntity = DiversLog.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<DiversLog> diversLogs = new ArrayList<>();

    @OneToMany(
            targetEntity = Equipment.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Equipment> equipments = new ArrayList<>();

    @OneToMany(
            targetEntity = DivingBase.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<DivingBase> divingBases = new ArrayList<>();
}
