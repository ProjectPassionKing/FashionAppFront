package com.example.fashionapp.Model.Entity.weather;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResult {
    @SerializedName("response")
    private WeatherResponse weatherResponse;

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public class WeatherResponse {
        @SerializedName("body")
        private WeatherBody weatherBody;

        public WeatherBody getWeatherBody() {
            return weatherBody;
        }
    }

    public class WeatherBody {
        @SerializedName("items")
        private WeatherItems itmes;

        public WeatherItems getItmes() {
            return itmes;
        }
    }

    public class WeatherItems {
        public List<WeatherItem> getItem() {
            return item;
        }
        @SerializedName("item")
        private List<WeatherItem> item;
    }

    public class WeatherItem {
        @SerializedName("category")
        private String category;
        @SerializedName("fcstTime")
        private String fcstTime;
        @SerializedName("fcstValue")
        private String fcstValue;

        public String getCategory() {
            return category;
        }

        public String getFcstTime() {
            return fcstTime;
        }

        public String getFcstValue() {
            return fcstValue;
        }
    }

}
