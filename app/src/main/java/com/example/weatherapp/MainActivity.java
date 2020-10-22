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

public class MainActivity extends AppCompatActivity {

    private TextView txtName;
    private  String arrNameCity[];

     private LinearLayout linearLayoutHorizon,linearLayoutVertical;
     private  LayoutInflater layoutInflaterItemsDaily,layoutInflaterItemsHourLy;
     private weather mainWeather;
     private  DataProvider dataProvider;
    private  String  url ="https://api.worldweatheronline.com/premium/v1/weather.ashx?key=5b9fefe967924430a45130625202010&q=Nha Trang&num_of_days=10&tp=1&format=json";

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
         dataProvider =DataProvider.GetInstance();
     }
     private  void requrl(){
         RequestQueue req = Volley.newRequestQueue(this);
         JsonObjectRequest reqObj =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 try {
                     JSONObject object = response.getJSONObject("data");
                     JSONArray array = object.getJSONArray("request");
                      JSONObject objectName = array.getJSONObject(0);
                     arrNameCity =objectName.get("query").toString().split(",");
                      txtName.setText(arrNameCity[0]);
                   //  Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
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