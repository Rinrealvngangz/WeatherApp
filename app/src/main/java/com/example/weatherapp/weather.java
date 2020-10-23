package com.example.weatherapp;

public class weather {

    public String getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(String max_temp) {
        this.max_temp = max_temp;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(String min_temp) {
        this.min_temp = min_temp;
    }

    private String max_temp;
    private  String min_temp;
    private String temp;
    private  String name;
    private  String status;
    private  String sunrise;
    private String sunset;
    private String  chance_of_rain;
    private String  humidity;
    private String  wind;
    private String  feelsLike;
    private String  precipitation;
    private String   pressure;
    private String visibility;
    private String uv_Index;

    private static weather instance =null;

    public  static weather GetInstance(){
        if(instance==null) instance =new weather();
        return  instance;
    }

    private void setInstance(weather Instance){ instance =Instance;}
    private   weather(){}


    public weather(String max_temp, String min_temp, String temp, String name, String status, String sunrise, String sunset, String chance_of_rain, String humidity, String wind, String feelsLike, String precipitation, String pressure, String visibility, String uv_Index) {
        this.temp = temp;
        this.name = name;
        this.status = status;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.chance_of_rain = chance_of_rain;
        this.humidity = humidity;
        this.wind = wind;
        this.feelsLike = feelsLike;
        this.precipitation = precipitation;
        this.pressure = pressure;
        this.visibility = visibility;
        this.uv_Index = uv_Index;
        this.max_temp =max_temp;
        this.min_temp =min_temp;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getChance_of_rain() {
        return chance_of_rain;
    }

    public void setChance_of_rain(String chance_of_rain) {
        this.chance_of_rain = chance_of_rain;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getUv_Index() {
        return uv_Index;
    }

    public void setUv_Index(String uv_Index) {
        this.uv_Index = uv_Index;
    }



    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
