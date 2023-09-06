package org.example.model;

import java.util.UUID;

public class HourTemperature {
    private String ID;
    private String time;
    private String icon;
    private String tempC;
    private String humidity;

    public HourTemperature() {
    }

    public HourTemperature(String time, String tempC, String humidity, String icon) {
        this.ID = UUID.randomUUID().toString();
        this.time = time;
        this.tempC = tempC;
        this.humidity = humidity;
        this.icon = icon;
    }

    public String getID() {
        return ID;
    }

    public String getTime() {
        return time;
    }

    public String getIcon() {
        return icon;
    }

    public String getTempC() {
        return tempC;
    }

    public String getHumidity() {
        return humidity;
    }

    public void printInfo() {
        System.out.println("--------------------------------------------------");
        System.out.println("Time: " + time);
        System.out.println("Temperature: " + tempC);
        System.out.println("Humidity: " + humidity);
        System.out.println("Temperature Image: " + icon);
    }
}
