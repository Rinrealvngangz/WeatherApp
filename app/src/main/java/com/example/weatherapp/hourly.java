package com.example.weatherapp;

public class hourly {

    public hourly(String time, String code, String temp) {
        this.time = time;
        this.code = code;
        this.temp = temp;
    }
    public hourly(){};

    private   String time;
    private  String code;
    private String temp;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIcon() {
        return code;
    }

    public void setIcon(String icon) {
        this.code = icon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }



}
