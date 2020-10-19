package com.example.weatherapp;

public class weather {

    public void setTemp(String temp) {
        this.temp = temp;
    }
    public void setName(String temp) {
        this.name = temp;
    }


    private static weather Instance;
    private String temp;
    private  String name;

    public static weather getInstance() {
        if(Instance ==null){ Instance =new weather(); return weather.Instance;}
        else
        return Instance;
    }


    private  void setInstance(weather instance) {
        Instance = instance;
    }

    private  weather(){}

    public void  weather(String temp, String name) {
        this.temp = temp;
        this.name = name;
    }


    public String getTemp() {
        return temp;
    }

    public String getName() {
        return name;
    }


}
