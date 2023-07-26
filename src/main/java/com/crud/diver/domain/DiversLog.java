package com.crud.diver.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DIVERS_LOG")
public class DiversLog {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE_OF_DIVING")
    private LocalDate dateOfDiving;

    @Column(name = "LOCALIZATION")
    private String localization;

    @Column(name = "VISIBILITY_M")
    private int visibility;

    @Column(name = "AIR_TEMPERATURE_C")
    private double airTemperature;

    @Column(name = "SURFACE_TEMPERATURE_C")
    private double surfaceTemperature;

    @Column(name = "BOTTOM_TEMPERATURE_C")
    private double bottomTemperature;

    @Column(name = "WEIGHT_KG")
    private double weight;

    @Column(name = "DEPTH_M")
    private double depth;

    @Column(name = "TIME_OF_DIVING_MINUTES")
    private int timeOfDiving;

    @Column(name = "TIME_IN")
    private LocalTime timeIn;

    @Column(name = "TIME_OUT")
    private LocalTime timeOut;

    @Column(name = "CONDITIONS")
    private String conditions;

    @Column(name = "AIR_USED_BAR")
    private int airUsed;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
