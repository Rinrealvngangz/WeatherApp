package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

     private  weather _weather;
     private  String name,temp;
     private LinearLayout linearLayoutHori,linearLayoutVerti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
        LayoutInflater layoutInflaterItemsHourLy = LayoutInflater.from(this);
        LayoutInflater layoutInflaterItemsDaily = LayoutInflater.from(this);


        for(int i =0;i<24;i++){
            View view = layoutInflaterItemsHourLy.inflate(R.layout.item,linearLayoutHori,false);
            linearLayoutHori.addView(view);
        }
        for(int i=0;i<10;i++){
            View view =layoutInflaterItemsDaily.inflate(R.layout.itemdaily,linearLayoutVerti,false);
            linearLayoutVerti.addView(view);

        }
    }
     private void initId(){
         linearLayoutHori =findViewById(R.id.itemHourLy);
         linearLayoutVerti =findViewById(R.id.itemsDaily);
     }
}