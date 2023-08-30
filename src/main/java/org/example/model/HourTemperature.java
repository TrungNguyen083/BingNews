package org.example.model;

import java.util.UUID;

public class HourTemperature {
    private String ID;
    private String hour;
    private String temperature;
    private String humidity;
    private String temperatureImg;

    public HourTemperature() {
    }

    public HourTemperature(String hour, String temperature, String humidity, String temperatureImg) {
        this.ID = UUID.randomUUID().toString();
        this.hour = hour;
        this.temperature = temperature;
        this.humidity = humidity;
        this.temperatureImg = temperatureImg;
    }

    public String getID() {
        return ID;
    }

    public String getHour() {
        return hour;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTemperatureImg() {
        return temperatureImg;
    }

    public void printInfo() {
        System.out.println("Hour: " + hour);
        System.out.println("Temperature: " + temperature);
        System.out.println("Humidity: " + humidity);
        System.out.println("Temperature Image: " + temperatureImg);
        System.out.println("--------------------------------------------------");
    }
}
