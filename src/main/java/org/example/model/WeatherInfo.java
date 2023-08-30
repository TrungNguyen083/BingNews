package org.example.model;

import java.util.List;
import java.util.UUID;

public class WeatherInfo {
    private String ID;
    private String place;
    private List<HourTemperature> listHourTemperature;
    private String date;

    public WeatherInfo() {
    }

    public WeatherInfo(String place, String date, List<HourTemperature> listHourTemperature) {
        this.ID = UUID.randomUUID().toString();
        this.place = place;
        this.date = date;
        this.listHourTemperature = listHourTemperature;
    }

    public String getID() {
        return ID;
    }

    public String getPlaceID() {
        return place;
    }

    public List<HourTemperature> getListHourTemperature() {
        return listHourTemperature;
    }

    public String getDate() {
        return date;
    }

    public void printInfo() {
        System.out.println("Place: " + place);
        System.out.println("Date: " + date);
        for (HourTemperature hourTemperature : listHourTemperature) {
            hourTemperature.printInfo();
        }
    }
}
