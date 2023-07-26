package com.crud.diver.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class DiversLogDto {
    private final Long id;
    private final LocalDate dateOfDiving;
    private final String localization;
    private final int visibility;
    private final double airTemperature;
    private final double surfaceTemperature;
    private final double bottomTemperature;
    private final double weight;
    private final double depth;
    private final int timeOfDiving;
    private final String conditions;
    private final LocalTime timeIn;
    private final LocalTime timeOut;
    private final int airUsed;
    private final Long userId;

    public static class DiversLogDtoBuilder {
        private Long id;
        private LocalDate dateOfDiving;
        private String localization;
        private int visibility;
        private double airTemperature;
        private double surfaceTemperature;
        private double bottomTemperature;
        private double weight;
        private double depth;
        private int timeOfDiving;
        private LocalTime timeIn;
        private LocalTime timeOut;
        private String conditions;
        private int airUsed;
        private Long userId;

        public DiversLogDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DiversLogDtoBuilder dateOfDiving(LocalDate dateOfDiving) {
            this.dateOfDiving = dateOfDiving;
            return this;

        }

        public DiversLogDtoBuilder localization(String localization) {
            this.localization = localization;
            return this;
        }

        public DiversLogDtoBuilder visibility(int visibility) {
            this.visibility = visibility;
            return this;
        }

        public DiversLogDtoBuilder airTemperature(double airTemperature) {
            this.airTemperature = airTemperature;
            return this;
        }

        public DiversLogDtoBuilder surfaceTemperature(double surfaceTemperature) {
            this.surfaceTemperature = surfaceTemperature;
            return this;
        }

        public DiversLogDtoBuilder bottomTemperature(double bottomTemperature) {
            this.bottomTemperature = bottomTemperature;
            return this;
        }

        public DiversLogDtoBuilder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public DiversLogDtoBuilder depth(double depth) {
            this.depth = depth;
            return this;
        }

        public DiversLogDtoBuilder timeOfDiving(int timeOfDiving) {
            this.timeOfDiving = timeOfDiving;
            return this;
        }

        public DiversLogDtoBuilder conditions(String conditions) {
            this.conditions = conditions;
            return this;
        }

        public DiversLogDtoBuilder timeIn(LocalTime timeIn) {
            this.timeIn = timeIn;
            return this;
        }

        public DiversLogDtoBuilder timeOut(LocalTime timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public DiversLogDtoBuilder airUsed(int airUsed) {
            this.airUsed = airUsed;
            return this;
        }

        public DiversLogDtoBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public DiversLogDto build() {
            return new DiversLogDto(id, dateOfDiving, localization, visibility, airTemperature, surfaceTemperature, bottomTemperature, weight, depth, timeOfDiving, conditions, timeIn, timeOut, airUsed, userId);
        }
    }

    private DiversLogDto(Long id, LocalDate dateOfDiving, String localization, int visibility, double airTemperature, double surfaceTemperature, double bottomTemperature, double weight, double depth, int timeOfDiving, String conditions, LocalTime timeIn, LocalTime timeOut, int airUsed, Long userId) {
        this.id = id;
        this.dateOfDiving = dateOfDiving;
        this.localization = localization;
        this.visibility = visibility;
        this.airTemperature = airTemperature;
        this.surfaceTemperature = surfaceTemperature;
        this.bottomTemperature = bottomTemperature;
        this.weight = weight;
        this.depth = depth;
        this.timeOfDiving = timeOfDiving;
        this.conditions = conditions;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.airUsed = airUsed;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateOfDiving() {
        return dateOfDiving;
    }

    public String getLocalization() {
        return localization;
    }

    public int getVisibility() {
        return visibility;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public double getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public double getBottomTemperature() {
        return bottomTemperature;
    }

    public double getWeight() {
        return weight;
    }

    public double getDepth() {
        return depth;
    }

    public int getTimeOfDiving() {
        return timeOfDiving;
    }

    public String getConditions() {
        return conditions;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public int getAirUsed() {
        return airUsed;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiversLogDto that = (DiversLogDto) o;

        if (visibility != that.visibility) return false;
        if (Double.compare(that.airTemperature, airTemperature) != 0) return false;
        if (Double.compare(that.surfaceTemperature, surfaceTemperature) != 0) return false;
        if (Double.compare(that.bottomTemperature, bottomTemperature) != 0) return false;
        if (Double.compare(that.weight, weight) != 0) return false;
        if (Double.compare(that.depth, depth) != 0) return false;
        if (timeOfDiving != that.timeOfDiving) return false;
        if (airUsed != that.airUsed) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(dateOfDiving, that.dateOfDiving)) return false;
        if (!Objects.equals(localization, that.localization)) return false;
        if (!Objects.equals(conditions, that.conditions)) return false;
        if (!Objects.equals(timeIn, that.timeIn)) return false;
        if (!Objects.equals(timeOut, that.timeOut)) return false;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateOfDiving != null ? dateOfDiving.hashCode() : 0);
        result = 31 * result + (localization != null ? localization.hashCode() : 0);
        result = 31 * result + visibility;
        temp = Double.doubleToLongBits(airTemperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(surfaceTemperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(bottomTemperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(depth);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + timeOfDiving;
        result = 31 * result + (conditions != null ? conditions.hashCode() : 0);
        result = 31 * result + (timeIn != null ? timeIn.hashCode() : 0);
        result = 31 * result + (timeOut != null ? timeOut.hashCode() : 0);
        result = 31 * result + airUsed;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
