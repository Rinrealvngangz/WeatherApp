package com.example.weatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaSync;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
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


    private ImageButton btnView;
    private  ImageView iconUrl,imgIconWeather;
    private TextView txtName,txtMaxTemp,txtMinTemp,txtUvIndex,txtSunrise,txtSunset,txtChance_of_rain
        ,txtDayBefore,txtMinTempBefore,txtMaxTempBefore,txtHumidity,txtWind,txtFeelsLike,txtPrecipi,txtPressure,txtVisibility,txtDay,txtStatus,txtTemp,tempItem,timeItem;
    private  String arrNameCity[];
  private  JSONObject objectName,objAstronomy;
   private   JSONArray arrayName,array,arrWeatherDesc;
     private  JSONArray arrayAstronomy,arrayHourly;
     private LinearLayout linearLayoutHorizon,linearLayoutVertical;
     private  LayoutInflater layoutInflaterItemsDaily,layoutInflaterItemsHourLy;
     private weather mainWeather;
     private  DataProvider dataProvider;
    private List<hourly> hourlyList;
    private  List<daily> dailyList;
private  JSONObject mJsonObject;
     public String  url ="https://api.worldweatheronline.com/premium/v1/weather.ashx?key=05c93b381e664757ae143628210601&q=DaLat_of_days=10&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
        initLayOut();
        EventClick();
        if(getIntent().hasExtra("json")) {
            try {
                mJsonObject = new JSONObject(getIntent().getStringExtra("json"));
               GetJson(mJsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            reqUrl();
        }
    }
    private void EventClick(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,listViewWeather.class);
                startActivity(intent);
            }
        });
    }
     private void initId(){

        btnView =findViewById(R.id.btn_view);
        hourlyList =new ArrayList<>();
        dailyList =new ArrayList<>();
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
                     JSONObject res =response.getJSONObject("data");
                     GetJson(res);
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
  private  void GetJson(JSONObject mJsonObject){
      try {
          arrayName = mJsonObject.getJSONArray("request");
          objectName = arrayName.getJSONObject(0);
          arrNameCity =objectName.get("query").toString().split(",");
          array = mJsonObject.getJSONArray("weather");
          JSONObject objTemp = array.getJSONObject(0);
          arrayAstronomy =objTemp.getJSONArray("astronomy");
          objAstronomy = arrayAstronomy.getJSONObject(0);
          arrayHourly =objTemp.getJSONArray("hourly");
          JSONObject  objAtIndex0 =arrayHourly.getJSONObject(0);
          arrWeatherDesc  = objAtIndex0.getJSONArray("weatherDesc");
          //Set format Date
          String day =objTemp.getString("date");
          txtDay.setText(setFormatterDayOfWeek(day));

        //init Class Weather
          mainWeather = dataProvider.getWeather(objTemp.get("maxtempC").toString(),objTemp.get("mintempC").toString(),objTemp.get("maxtempC").toString(),
                  arrNameCity[0],arrWeatherDesc.getJSONObject(0).get("value").toString(),objAstronomy.get("sunrise").toString(),
                  objAstronomy.get("sunset").toString(),objAtIndex0.get("chanceofrain").toString(),objAtIndex0.get("humidity").toString(),
                  objAtIndex0.get("windspeedKmph").toString()+"km/h",objAtIndex0.get("FeelsLikeC").toString()+"˚",objAtIndex0.get("precipMM").toString()+" cm",
                  objAtIndex0.get("pressure").toString()+"hPa",objAtIndex0.get("visibility").toString()+"km",objTemp.get("uvIndex").toString());
         //Set Text
          txtStatus.setText(mainWeather.getStatus());
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
          txtName.setText(mainWeather.getName());
            //Get Daily
          for(int j=1; j<array.length();j++) {
              JSONObject obj = array.getJSONObject(j);
              //    Toast.makeText(MainActivity.this,obj.toString(),Toast.LENGTH_LONG).show();
              String date = obj.getString("date");
              String maxTemp = obj.getString("mintempC");
              int minTempInt = Integer.parseInt(maxTemp) - 6;
              String minTemp = String.valueOf(minTempInt);
              String icon = obj.getJSONArray("hourly").getJSONObject(0).get("weatherCode").toString();
              daily daily = new daily(date, icon, maxTemp, minTemp);
              dailyList.add(daily);
          }
              for ( daily items :
                      dailyList  ) {
                  String uri,dateFormat;
                  View view =layoutInflaterItemsDaily.inflate(R.layout.itemdaily,linearLayoutVertical,false);
                  txtDayBefore =view.findViewById(R.id.txtDayBefore);
                  imgIconWeather =view.findViewById(R.id.iconWeather);
                  txtMaxTempBefore =view.findViewById(R.id.txtMaxTemp);
                  txtMinTempBefore =view.findViewById(R.id.txtMinTemp);
                  int code = Integer.parseInt(items.getIconDay());
                  uri = setIcon_Code(code);
                  int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                  Drawable res = getResources().getDrawable(imageResource);
                  imgIconWeather.setImageDrawable(res);
                  txtDayBefore.setText(items.getDay());
                  dateFormat =  setFormatterDayOfWeek(items.getDay());
                  txtDayBefore.setText(dateFormat);
                  txtMinTempBefore.setText(items.getMinTempDay());
                  txtMaxTempBefore.setText(items.getMaxTempDay());
                  linearLayoutVertical.addView(view);
              }
              for(int i=0;i<arrayHourly.length();i++){
                  JSONObject obj = arrayHourly.getJSONObject(i);
                  String time =obj.getString("time");
                  int timeTemp = Integer.parseInt(time);
                  time =  setFormatTime(timeTemp);
                  String temp =obj.getString("tempC");
                  String weatherCode = obj.getString("weatherCode");
                  hourly hourly =new hourly(time,weatherCode,temp);
                  hourlyList.add(hourly);
              }
          //Get Hourly
          for ( hourly items: hourlyList
          ) {
              String uri;
              View view = layoutInflaterItemsHourLy.inflate(R.layout.item,linearLayoutHorizon,false);
              tempItem =view.findViewById(R.id.tempItem);
              timeItem =view.findViewById(R.id.timeItem);
              iconUrl =view.findViewById(R.id.iconItem);
              int code = Integer.parseInt(items.getIcon());
              uri= setIcon_Code(code);
              int imgRes =getResources().getIdentifier(uri,null,getPackageName());
              Drawable res = getResources().getDrawable(imgRes);
              iconUrl.setImageDrawable(res);
              tempItem.setText(items.getTemp());
              timeItem.setText(items.getTime());
              linearLayoutHorizon.addView(view);
          }
      } catch (JSONException e) {
          e.printStackTrace();
      }
  }
     private  String setIcon_Code(int code){
         String uri;
         if( code == 359 ||code ==308 ||code ==302 || code ==305){
             uri = "@drawable/torrential_rain_359";  // where myresource (without the extension) is the file
         }else if(code ==116){
             uri = "@drawable/smiling_sun";
         }else if(code ==122 || code ==200){
             uri = "@drawable/cloud_lightning";
         }else if(code ==143){
             uri ="@drawable/mist";
         }else if(code ==176 || code ==263 ||code ==353){
             uri ="@drawable/light_rain_shower";
         }else if(code ==179){
             uri ="@drawable/sleet_shower";
         }else if(code ==374 || code ==377 || code ==365 || code ==362 ||code ==350 || code ==317|| code ==314 ||code ==311|| code ==182 ||code ==185 || code ==281 || code ==284){
             uri ="@drawable/cloud_with_sleet";
         }else if(code ==395 ||code == 371||code ==368 ||code ==227 || code ==320 || code ==323 || code ==326){
             uri ="@drawable/snow";
         }else if( code ==386 ||code ==230 || code ==296 ||code ==356){
             uri ="@drawable/rain_heavy";
         }else if(code ==248 || code ==260){
             uri ="@drawable/fog";
         }else if(code ==266 || code ==293 ){
             uri ="@drawable/cloudy_light_rain";
         }else if(code ==329 || code ==332 || code ==335 || code ==338){
             uri ="@drawable/cloudy_light_rain";
         }else {
             uri ="@drawable/stormy_weather";
         }
         return  uri;
     }
     private  String setFormatTime(int timeTemp ){
        String time="";
         switch (timeTemp){
             case 0:
                 time ="0 AM";

             break;
             case 100:
                 time ="1 AM";

                 break;
             case 200:
                 time ="2 AM";

                 break;
             case 300:
                 time ="3 AM";

                 break;
             case 400:
                 time ="4 AM";

                 break;
             case 500:
                 time ="5 AM";

                 break;
             case 600:
                 time ="6 AM";

                 break;
             case 700:
                 time ="7 AM";

                 break;
             case 800:
                 time ="8 AM";

                 break;
             case 900:
                 time ="9 AM";

                 break;
             case 1000:
                 time ="10 AM";

                 break;
             case 1100:
                 time ="11 AM";

                 break;
             case 1200:
                 time ="0 PM";

                 break;
             case 1300:
                 time ="1 PM";

                 break;
             case 1400:
                 time ="2 PM";

                 break;
             case 1500:
                 time ="3 PM";

                 break;
             case 1600:
                 time ="4 PM";

                 break;
             case 1700:
                 time ="5 PM";

                 break;
             case 1800:
                 time ="6 PM";

                 break;
             case 1900:
                 time ="7 PM";

                 break;
             case 2000:
                 time ="8 PM";

                 break;
             case 2100:
                 time ="9 PM";

                 break;
             case 2200:
                 time ="10 PM";

                 break;
             case 2300:
                 time ="11 PM";

                 break;
         }
         return  time;
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
         SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
         String stringDate = sdf.format(date);
         return  stringDate;

     }
     private  void initLayOut(){
         layoutInflaterItemsHourLy = LayoutInflater.from(this);
         layoutInflaterItemsDaily = LayoutInflater.from(this);

     }



}