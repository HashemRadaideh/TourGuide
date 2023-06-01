package com.TourGuide.model;

/**
 * City
 */
public class City {
    private String name;
    private CityStatus status;
    private int trafficLightViolations;
    private int stopSignViolations;
    private int jaywalkingViolations;
    private int litteringViolations;
    private boolean isDangerous;
    private boolean isInsane;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityStatus getStatus() {
        return status;
    }

    public void setStatus(CityStatus status) {
        this.status = status;
    }

    public int getTrafficLightViolations() {
        return trafficLightViolations;
    }

    public void setTrafficLightViolations(int trafficLightViolations) {
        this.trafficLightViolations = trafficLightViolations;
    }

    public int getStopSignViolations() {
        return stopSignViolations;
    }

    public void setStopSignViolations(int stopSignViolations) {
        this.stopSignViolations = stopSignViolations;
    }

    public int getJaywalkingViolations() {
        return jaywalkingViolations;
    }

    public void setJaywalkingViolations(int jaywalkingViolations) {
        this.jaywalkingViolations = jaywalkingViolations;
    }

    public int getLitteringViolations() {
        return litteringViolations;
    }

    public void setLitteringViolations(int litteringViolations) {
        this.litteringViolations = litteringViolations;
    }

    public boolean isDangerous() {
        return isDangerous;
    }

    public void setDangerous(boolean isDangerous) {
        this.isDangerous = isDangerous;
    }

    public boolean isInsane() {
        return isInsane;
    }

    public void setInsane(boolean isInsane) {
        this.isInsane = isInsane;
    }
}
