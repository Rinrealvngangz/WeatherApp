package com.example.weatherapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class lv_weather_adapter extends BaseAdapter {

    TextView time,temp,name;
    Context myContext;
    int myLayout;
    List<listWeather> myListWeather;

    public  lv_weather_adapter(Context context, int layout, List<listWeather> listWeather){
         myContext =context;
         myLayout =layout;
         myListWeather =listWeather;
    }
    @Override


    public int getCount() {
        return  myListWeather.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =layoutInflater.inflate(myLayout,null);
         time =convertView.findViewById(R.id.txtTime_Lv);
         name =convertView.findViewById(R.id.txtName_Lv);
         temp =convertView.findViewById(R.id.txtTemp_Lv);
         time.setText(myListWeather.get(position).getTime());
         name.setText(myListWeather.get(position).getName());
         temp.setText(myListWeather.get(position).getTemp());
        return convertView;
    }
}
