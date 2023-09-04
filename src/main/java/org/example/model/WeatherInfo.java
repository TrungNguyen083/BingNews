package org.example.model;

import java.util.List;
import java.util.UUID;

public class WeatherInfo {
    private String ID;
    private String locationName;
    private List<HourTemperature> listHourTemperature;
    private String localTime;

    public WeatherInfo() {
    }

    public WeatherInfo(String locationName, String localTime, List<HourTemperature> listHourTemperature) {
        this.ID = UUID.randomUUID().toString();
        this.locationName = locationName;
        this.localTime = localTime;
        this.listHourTemperature = listHourTemperature;
    }

    public String getID() {
        return ID;
    }

    public String getLocationName() {
        return locationName;
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
}
