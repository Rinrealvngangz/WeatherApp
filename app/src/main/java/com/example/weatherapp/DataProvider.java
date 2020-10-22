package com.example.weatherapp;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    private  List<weather> weathers;
    private  List<daily>dailyList;
    private  List<hourly>hourlyList;
    private  weather _weather;
    private static  DataProvider  instance =null;

    public  static DataProvider GetInstance(){
        if(instance==null) instance =new DataProvider();
        return  instance;
    }

    private void setInstance(DataProvider dataProvider){ instance =dataProvider;}

    private DataProvider(){
      dailyList =new ArrayList<>();
       hourlyList =new ArrayList<>();

    };

    public  weather getWeather(){
        _weather =weather.GetInstance();
        return _weather;
    }

}