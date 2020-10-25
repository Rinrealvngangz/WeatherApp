package com.example.weatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaSync;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private  ImageView iconUrl;
    private TextView txtName,txtMaxTemp,txtMinTemp,txtUvIndex,txtSunrise,txtSunset,txtChance_of_rain
            ,txtHumidity,txtWind,txtFeelsLike,txtPrecipi,txtPressure,txtVisibility,txtDay,txtStatus,txtTemp,tempItem,timeItem;
    private  String arrNameCity[];
  private  JSONObject objectName,objAstronomy;
   private   JSONArray arrayName,array,arrWeatherDesc;
     private  JSONArray arrayAstronomy,arrayHourly;
     private LinearLayout linearLayoutHorizon,linearLayoutVertical;
     private  LayoutInflater layoutInflaterItemsDaily,layoutInflaterItemsHourLy;
     private weather mainWeather;
     private  hourly mainHourly;
     private  DataProvider dataProvider;
    private List<hourly> hourlyList;

     public String  url ="https://api.worldweatheronline.com/premium/v1/weather.ashx?key=5b9fefe967924430a45130625202010&q=DaLat&tp=1&num_of_days=10&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
        reqUrl();
        getValueWeather();
        initLayOut();

    }
     private void initId(){

        hourlyList =new ArrayList<>();
         linearLayoutHorizon =findViewById(R.id.itemHourLy);
         linearLayoutVertical =findViewById(R.id.itemsDaily);
         txtName =findViewById(R.id.txtName);
         txtMaxTemp = findViewById(R.id.txtMaxTemp);
         txtMinTemp =findViewById(R.id.txtMinTemp);
         txtUvIndex =findViewById(R.id.value_uvIndex);
         dataProvider =DataProvider.GetInstance();
         txtSunrise =findViewById(R.id.value_sunrise);
         txtSunset =findViewById(R.id.value_sunset);
        txtChance_of_rain =findViewById(R.id.value_chance_of_rain);
        txtHumidity =findViewById(R.id.value_humidity);
        txtWind =findViewById(R.id.value_wind);
        txtFeelsLike =findViewById(R.id.value_feelslike);
        txtPrecipi =findViewById(R.id.value_precipitation);
        txtPressure =findViewById(R.id.value_pressure);
        txtVisibility =findViewById(R.id.value_visibility);
        txtDay =findViewById(R.id.txtDate);
     txtStatus =findViewById(R.id.txtStatus);
     txtTemp =findViewById(R.id.txtTemp);
     }

    private  void reqUrl(){
         RequestQueue req = Volley.newRequestQueue(this);
         JsonObjectRequest reqObj =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
             @RequiresApi(api = Build.VERSION_CODES.O)
             @Override
             public void onResponse(JSONObject response) {
                 try {
                     //root
                  JSONObject  object = response.getJSONObject("data");

                   //reqNameCity
                      arrayName = object.getJSONArray("request");
                      objectName = arrayName.getJSONObject(0);
                     arrNameCity =objectName.get("query").toString().split(",");
                    //nodeWeather
                      array = object.getJSONArray("weather");

                     JSONObject objTemp = array.getJSONObject(0);

                     //Get Date
                       String day =objTemp.getString("date");
                      txtDay.setText(setFormatterDayOfWeek(day));

                     mainWeather.setUv_Index(objTemp.get("uvIndex").toString());
                      mainWeather.setMax_temp(objTemp.get("maxtempC").toString());
                     mainWeather.setMin_temp(objTemp.get("mintempC").toString());
                     mainWeather.setTemp(objTemp.get("mintempC").toString());
                     arrayAstronomy =objTemp.getJSONArray("astronomy");
                     objAstronomy = arrayAstronomy.getJSONObject(0);
                     arrayHourly =objTemp.getJSONArray("hourly");
                   JSONObject  objAtIndex0 =arrayHourly.getJSONObject(0);
                   //get WeatherDesc
                   arrWeatherDesc  =   objAtIndex0.getJSONArray("weatherDesc");
                     mainWeather.setStatus(arrWeatherDesc.getJSONObject(0).get("value").toString());
                     txtStatus.setText(mainWeather.getStatus());
                     //At obj Index 0
                     mainWeather.setSunrise(objAstronomy.get("sunrise").toString());
                     mainWeather.setSunset(objAstronomy.get("sunset").toString());
                     mainWeather.setChance_of_rain(objAtIndex0.get("chanceofrain").toString());
                      mainWeather.setHumidity(objAtIndex0.get("humidity").toString());
                     mainWeather.setWind(objAtIndex0.get("windspeedKmph").toString()+"km/h");
                     mainWeather.setFeelsLike(objAtIndex0.get("FeelsLikeC").toString()+"˚");
                     mainWeather.setPrecipitation(objAtIndex0.get("precipMM").toString()+" cm");
                     mainWeather.setPressure(objAtIndex0.get("pressure").toString()+"hPa");
                     mainWeather.setVisibility(objAtIndex0.get("visibility").toString()+"km");

                      txtMaxTemp.setText(mainWeather.getMax_temp());
                     txtMinTemp.setText(mainWeather.getMin_temp());
                     txtTemp.setText(mainWeather.getTemp()+"˚");
                     txtChance_of_rain.setText(mainWeather.getChance_of_rain());
                     txtHumidity.setText(mainWeather.getHumidity());
                     txtWind.setText(mainWeather.getWind());
                     txtFeelsLike.setText(mainWeather.getFeelsLike());
                     txtPrecipi.setText(mainWeather.getPrecipitation());
                     txtPressure.setText(mainWeather.getPressure());
                     txtVisibility.setText(mainWeather.getVisibility());
                     txtUvIndex.setText(mainWeather.getUv_Index());
                     txtSunrise.setText(mainWeather.getSunrise());
                     txtSunset.setText(mainWeather.getSunset());
                     txtChance_of_rain.setText(mainWeather.getChance_of_rain()+"%");
                     mainWeather.setName(arrNameCity[0]);
                      txtName.setText(mainWeather.getName());

                      //Get Hourly
                     for(int i=0;i<arrayHourly.length();i++){
                             JSONObject obj = arrayHourly.getJSONObject(i);
                             String time =obj.getString("time");
                             String temp =obj.getString("tempC");
                             String weatherCode = obj.getString("weatherCode");
                                     hourly hourly =new hourly(time,weatherCode,temp);
                                     hourlyList.add(hourly);

                     }
                     for ( hourly items: hourlyList
                          ) {
                         String uri="";
                         View view = layoutInflaterItemsHourLy.inflate(R.layout.item,linearLayoutHorizon,false);
                         tempItem =view.findViewById(R.id.tempItem);
                         timeItem =view.findViewById(R.id.timeItem);
                         iconUrl =view.findViewById(R.id.iconItem);
                         int code = Integer.parseInt(items.getIcon());

                         if( code == 359 ||code ==308 ||code ==302){
                              uri = "@drawable/torrential_rain_359";  // where myresource (without the extension) is the file

                         }else if(code ==116){
                             uri = "@drawable/clear_sky_night";
                         }else if(code ==122){
                             uri = "@drawable/cloud_lightning";
                         }

                             int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                         Drawable res = getResources().getDrawable(imageResource);
                         iconUrl.setImageDrawable(res);
                         tempItem.setText(items.getTemp());
                         timeItem.setText(items.getTime());
                         linearLayoutHorizon.addView(view);
                         //Toast.makeText(MainActivity.this,items.getIcon(),Toast.LENGTH_LONG).show();
                     }

                } catch (JSONException e) {
                     e.printStackTrace();
                }

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
             }
         }
         );
           req.add(reqObj);
     }


    private  String setFormatterDayOfWeek(String dmy){

         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
         String dateInString = dmy;
         Date date = null;
         try {
             date = formatter.parse(dateInString);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");

         String stringDate2 = sdf2.format(date);
         return  stringDate2;

     }
     private  void initLayOut(){
         layoutInflaterItemsHourLy = LayoutInflater.from(this);
         layoutInflaterItemsDaily = LayoutInflater.from(this);



         for(int i=0;i<10;i++){
             View view =layoutInflaterItemsDaily.inflate(R.layout.itemdaily,linearLayoutVertical,false);
             linearLayoutVertical.addView(view);

         }
     }
     private  void getValueWeather(){
            mainWeather = dataProvider.getWeather();

     }


}