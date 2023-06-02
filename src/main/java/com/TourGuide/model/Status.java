package com.TourGuide.model;

public class Status {
    private boolean date = true;

    public boolean isDate() {
        return date;
    }

    public void setDate(boolean date) {
        this.date = date;
    }

    private boolean isNumber = true;

    public boolean isNumber() {
        return isNumber;
    }

    public void setNumber(final boolean isNumber) {
        this.isNumber = isNumber;
    }

    private boolean isCountry = true;

    public boolean isCountry() {
        return isCountry;
    }

    public void setCountry(final boolean isCountry) {
        this.isCountry = isCountry;
    }

    private boolean isCity = true;

    public boolean isCity() {
        return isCity;
    }

    public void setCity(final boolean isCity) {
        this.isCity = isCity;
    }

    private boolean isViolation = true;

    public boolean isViolation() {
        return isViolation;
    }

    public void setViolation(final boolean isViolation) {
        this.isViolation = isViolation;
    }
}
