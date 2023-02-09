package com.example.fashionapp.Model.Entity;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("body")
    private WeatherBody weatherBody;

    public WeatherBody getWeatherBody() {
        return weatherBody;
    }
}
