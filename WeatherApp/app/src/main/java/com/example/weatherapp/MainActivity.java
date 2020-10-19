package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

     private  weather _weather;
     private  String name,temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       _weather = weather.getInstance();
       _weather.weather(temp,name);
      String Name = _weather.getName();
      String Temp =  _weather.getTemp();
    }

}