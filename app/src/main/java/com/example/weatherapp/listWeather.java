package com.example.weatherapp;

import org.json.JSONObject;

public class listWeather {
    public listWeather(String name, String temp, String time,JSONObject jsonObject) {
        this.name = name;
        this.temp = temp;
        this.time = time;
        this.jsonObject =jsonObject;
    }
public  listWeather(){}
    public  String name;
    public String temp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String time;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject jsonObject;
}
