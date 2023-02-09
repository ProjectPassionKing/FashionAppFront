package com.example.fashionapp.Model.Entity;

import com.google.gson.annotations.SerializedName;

public class WeatherItem {
    @SerializedName("baseDate")
    private String baseDate;
    @SerializedName("baseTime")
    private String baseTime;
    @SerializedName("category")
    private String category;
    @SerializedName("fcstDate")
    private String fcstDate;
    @SerializedName("fcstTime")
    private String fcstTime;
    @SerializedName("fcstValue")
    private String fcstValue;
    @SerializedName("nx")
    private int nx;
    @SerializedName("ny")
    private int ny;

    public String getCategory() {
        return category;
    }

    public String getFcstDate() {
        return fcstDate;
    }

    public String getFcstTime() {
        return fcstTime;
    }

    public String getFcstValue() {
        return fcstValue;
    }
}
