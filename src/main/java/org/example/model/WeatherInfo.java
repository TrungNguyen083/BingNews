package org.example.model;

import java.util.List;
import java.util.UUID;

public class WeatherInfo {
    private String ID;
    private String locationName;

    private String countryName;
    private String localTime;
    private List<HourTemperature> listHourTemperature;
    private Pagination pagination;

    public WeatherInfo() {
    }

    public WeatherInfo(String locationName, String countryName, String localTime, List<HourTemperature> listHourTemperature) {
        this.ID = UUID.randomUUID().toString();
        this.locationName = locationName;
        this.countryName = countryName;
        this.localTime = localTime;
        this.listHourTemperature = listHourTemperature;
    }

    public String getID() {
        return ID;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getCountryName() {
        return countryName;
    }

    public List<HourTemperature> getListHourTemperature() {
        return listHourTemperature;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void printInfo() {
        System.out.println("Location Name: " + locationName);
        System.out.println("Local Time: " + localTime);
        for (HourTemperature hourTemperature : listHourTemperature) {
            hourTemperature.printInfo();
        }
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
