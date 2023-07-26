package com.crud.diver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DivingBaseDto {
    private Long id;
    private String name;
    private String localization;
    private String currencyName;
    private double currencyRate;
    private double temperature;
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DivingBaseDto that = (DivingBaseDto) o;

        if (Double.compare(that.currencyRate, currencyRate) != 0) return false;
        if (Double.compare(that.temperature, temperature) != 0) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(localization, that.localization)) return false;
        if (!Objects.equals(currencyName, that.currencyName)) return false;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (localization != null ? localization.hashCode() : 0);
        result = 31 * result + (currencyName != null ? currencyName.hashCode() : 0);
        temp = Double.doubleToLongBits(currencyRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
