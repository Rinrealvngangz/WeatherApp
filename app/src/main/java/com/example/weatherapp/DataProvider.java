package com.example.weatherapp;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    private  List<weather> weathers;
    private  List<daily>dailyList;

    public List<hourly> getHourlyList() {
        return hourlyList;
    }

    private  List<hourly>hourlyList;
    private  weather _weather;
    private  hourly _hourly;
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
    public void getHourly(String time,String temp,String icon){
        _hourly =new hourly(time,icon,temp);
        hourlyList.add(_hourly);
    }

    public  weather getWeather(){
        _weather =weather.GetInstance();
        return _weather;
    }

}
