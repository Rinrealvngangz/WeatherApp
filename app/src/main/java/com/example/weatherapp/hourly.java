package com.example.weatherapp;

public class hourly {

    public hourly(String time, String icon, String temp) {
        this.time = time;
        this.icon = icon;
        this.temp = temp;
    }
    public hourly(){};

    private   String time;
    private  String icon;
    private String temp;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }



}
