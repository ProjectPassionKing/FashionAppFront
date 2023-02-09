package com.example.fashionapp.Model.Entity;

import com.google.gson.annotations.SerializedName;

public class WeatherResult {

//    String response;
    @SerializedName("response")
    private WeatherResponse weatherResponse;

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }
}
