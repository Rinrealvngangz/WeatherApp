package com.example.weatherapp;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    private  weather _weather;
    private static  DataProvider  instance =null;

    public  static DataProvider GetInstance(){
        if(instance==null) instance =new DataProvider();
        return  instance;
    }
    private void setInstance(DataProvider dataProvider){ instance =dataProvider;}
    private DataProvider(){
    };

    public  weather getWeather(String max_temp, String min_temp, String temp, String name, String status, String sunrise, String sunset, String chance_of_rain, String humidity, String wind, String feelsLike, String precipitation, String pressure, String visibility, String uv_Index){
        _weather =weather.GetInstance(max_temp, min_temp, temp, name, status, sunrise, sunset, chance_of_rain, humidity, wind, feelsLike, precipitation, pressure, visibility, uv_Index);
        return _weather;
    }


}
