package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.data.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class listViewWeather extends AppCompatActivity {
  ListView listViewWeather;
  ArrayList<listWeather> arrayList;
  ImageButton btnFind;
  EditText editText;
  String query="";
  private  JSONArray arrayName,array;
  private JSONObject objectName;
    private  String arrNameCity[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_weather);
        btnFind =findViewById(R.id.btnFind);
        editText =findViewById(R.id.editTextQuery);
        listViewWeather =findViewById(R.id.item_Lv);
        arrayList =new ArrayList<listWeather>();
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              query = editText.getText().toString();
                   reqUrl(query);
            }
        });
           EventClickBtn();

    }
    public final static String EXTRA_MESSAGE = "json";
    private void EventClickBtn(){
       listViewWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             //  Toast.makeText(com.example.weatherapp.listViewWeather.this, arrayList.get(position).getJsonObject().toString(),Toast.LENGTH_LONG).show();
               Intent intent = new Intent(listViewWeather.this, MainActivity.class);
               intent.putExtra(EXTRA_MESSAGE, arrayList.get(position).getJsonObject().toString());
               startActivity(intent);
           }
       });
    }
    private  void reqUrl(String query){
        String  url ="https://api.worldweatheronline.com/premium/v1/weather.ashx?key=5b9fefe967924430a45130625202010&q="+query+"&tp=1&num_of_days=10&format=json";
        RequestQueue req = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                  JSONObject object = response.getJSONObject("data");
                    //reqNameCity
                   arrayName = object.getJSONArray("request");
                    objectName = arrayName.getJSONObject(0);
                    arrNameCity =objectName.get("query").toString().split(",");
                    String temp = object.getJSONArray("weather").getJSONObject(0).get("maxtempC").toString()+"˚";
                    Date date=java.util.Calendar.getInstance().getTime();
                    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("HH:mm");
                    String stringDate2 = simpleDateFormat.format(date);
                    listWeather listWeather =new listWeather(arrNameCity[0],temp,stringDate2,object);
                    arrayList.add(listWeather);
                    lv_weather_adapter lv_weather_adapter =new lv_weather_adapter(listViewWeather.this,R.layout.activity_row__weather,arrayList);
                    listViewWeather.setAdapter(lv_weather_adapter);
                   //Toast.makeText(listViewWeather.this,arrNameCity[0],Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        req.add(jsonObjectRequest);

    }
}