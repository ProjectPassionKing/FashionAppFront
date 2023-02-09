package com.example.fashionapp.Model.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherItems {
    public List<WeatherItem> getItem() {
        return item;
    }
    @SerializedName("item")
    private List<WeatherItem> item;
}
