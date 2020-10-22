package com.example.weatherapp;

public class daily {

    private  String day;
    private  String iconDay;
    private  String maxTempDay;
    private  String minTempDay;

    public daily(String day, String iconDay, String maxTempDay, String minTempDay) {
        this.day = day;
        this.iconDay = iconDay;
        this.maxTempDay = maxTempDay;
        this.minTempDay = minTempDay;
    }

    public   daily(){};

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getIconDay() {
        return iconDay;
    }

    public void setIconDay(String iconDay) {
        this.iconDay = iconDay;
    }

    public String getMaxTempDay() {
        return maxTempDay;
    }

    public void setMaxTempDay(String maxTempDay) {
        this.maxTempDay = maxTempDay;
    }

    public String getMinTempDay() {
        return minTempDay;
    }

    public void setMinTempDay(String minTempDay) {
        this.minTempDay = minTempDay;
    }

}
