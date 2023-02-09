package com.example.fashionapp.Model.Entity;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBody {
    @SerializedName("dataType")
    private String dataType;
    @SerializedName("pageNo")
    private int pageNo;
    @SerializedName("numOfRows")
    private int numOfRows;
    @SerializedName("totalCount")
    private int totalCount;

    public WeatherItems getItmes() {
        return itmes;
    }

    @SerializedName("items")
    private WeatherItems itmes;

}
