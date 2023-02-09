package com.example.fashionapp.Model;

import com.example.fashionapp.BuildConfig;
import com.example.fashionapp.Model.Entity.weather.WeatherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("getVilageFcst?serviceKey="+ BuildConfig.WEATHER_KEY)
    Call<WeatherResult> weatherRes(
            @Query("nx") String nx,
            @Query("ny") String ny,
            @Query("base_date") String base_date,
            @Query("base_time") String base_time,
            @Query("dataType") String dataType,
            @Query("pageNo") String pageNo,
            @Query("numOfRows") String rownum
            );
}
