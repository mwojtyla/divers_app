package com.crud.diver.domain;

import lombok.*;
import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DIVING_BASE")
public class DivingBase {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCALIZATION")
    private String localization;

    @Column(name = "CURRENCY_NAME")
    private String currencyName;

    @Column(name = "CURRENCY_RATE_PLN")
    private double currencyRate;

    @Column(name = "TEMPERATURE_C")
    private double temperature;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
