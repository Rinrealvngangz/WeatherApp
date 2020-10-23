package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaSync;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView txtName,txtMaxTemp,txtMinTemp,txtUvIndex,txtSunrise,txtSunset,txtChance_of_rain
            ,txtHumidity,txtWind,txtFeelsLike,txtPrecipi,txtPressure,txtVisibility,txtDay;
  private  JSONArray array;
    private  String arrNameCity[];
  private  JSONObject objectName,objAstronomy;
   private   JSONArray arrayName;
     private  JSONArray arrayAstronomy,arrayHourly;
     private LinearLayout linearLayoutHorizon,linearLayoutVertical;
     private  LayoutInflater layoutInflaterItemsDaily,layoutInflaterItemsHourLy;
     private weather mainWeather;
     private  DataProvider dataProvider;
    private  String  url ="https://api.worldweatheronline.com/premium/v1/weather.ashx?key=5b9fefe967924430a45130625202010&q=Da Lat&num_of_days=10&tp=1&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
        requrl();
        getValueWeather();
        initLayOut();


    }
     private void initId(){
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

     }

    private  void requrl(){
         RequestQueue req = Volley.newRequestQueue(this);
         JsonObjectRequest reqObj =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
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

                     mainWeather.setUv_Index(objTemp.get("uvIndex").toString());
                      mainWeather.setMax_temp(objTemp.get("maxtempC").toString());
                     mainWeather.setMin_temp(objTemp.get("mintempC").toString());

                     arrayAstronomy =objTemp.getJSONArray("astronomy");
                     objAstronomy = arrayAstronomy.getJSONObject(0);
                     arrayHourly =objTemp.getJSONArray("hourly");
                   JSONObject  objAtIndex0 =arrayHourly.getJSONObject(0);
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

                     Calendar c = Calendar.getInstance();
                     Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("23/10/2020");

                     c.setTime(date1); // yourdate is an object of type Date

                     int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                  Toast.makeText(MainActivity.this,dayOfWeek,Toast.LENGTH_LONG).show();
                } catch (JSONException | ParseException e) {
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

     private  void initLayOut(){
         layoutInflaterItemsHourLy = LayoutInflater.from(this);
         layoutInflaterItemsDaily = LayoutInflater.from(this);


         for(int i =0;i<24;i++){
             View view = layoutInflaterItemsHourLy.inflate(R.layout.item,linearLayoutHorizon,false);
             linearLayoutHorizon.addView(view);
         }
         for(int i=0;i<10;i++){
             View view =layoutInflaterItemsDaily.inflate(R.layout.itemdaily,linearLayoutVertical,false);
             linearLayoutVertical.addView(view);

         }
     }
     private  void getValueWeather(){
            mainWeather = dataProvider.getWeather();
     }


}