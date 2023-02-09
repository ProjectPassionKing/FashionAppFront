package com.example.fashionapp.View;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.fashionapp.Model.Entity.weather.Weather;
import com.example.fashionapp.R;

public class WeatherView extends ConstraintLayout {
    public WeatherView(@NonNull Activity activity) {
        super(activity.getApplicationContext());
        init(activity);
    }

    private void init(Activity activity) {
        LayoutInflater inflater = (LayoutInflater) activity.getApplication().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.weather, this, true);
    }

    public void setWeatherUI(Weather weather){
        String c = getTxtfromStr(R.string.temperature);

        ((TextView) findViewById(R.id.temperature_txt)).setText(weather.getTmp()+c);
        ((TextView) findViewById(R.id.mtemperature_txt)).setText(weather.getMinTmp() +c + "/" + weather.getMaxTmp()+c);

        String sno = weather.getSnow();
        String sky = weather.getSky();
        String pty = weather.getRain();
        String pcp = weather.getRainper();

        if (!sno.equals("적설없응")){
            skycondition(sky, pty, sno);
        }
        skycondition(sky, pty, pcp);
    }

    private void skycondition(String sky, String pty, String pcp) {
        if (pty.equals("0")) {
            if (sky.equals("1")) {
                changeweathertxt(getTxtfromStr(R.string.sky1), R.drawable.day_clear);
            }
            if (sky.equals("2")) {
                changeweathertxt(getTxtfromStr(R.string.sky2), R.drawable.day_partial_cloud);
            }
            if (sky.equals("3")) {
                changeweathertxt(getTxtfromStr(R.string.sky3), R.drawable.cloudy);
            }
            if (sky.equals("4")) {
                changeweathertxt(getTxtfromStr(R.string.sky4), R.drawable.overcast);
            }
        }
        if (pty.equals("1")) {
            changeweathertxt(getTxtfromStr(R.string.rain) + pcp, R.drawable.rain);
        }
        if (pty.equals("2")) {
            changeweathertxt(getTxtfromStr(R.string.rainsnow) + pcp, R.drawable.sleet);
        }
        if (pty.equals("3")) {
            changeweathertxt(getTxtfromStr(R.string.snow) + pcp, R.drawable.snow);
        }
        if (pty.equals("4")) {
            changeweathertxt(getTxtfromStr(R.string.shower) + pcp, R.drawable.day_rain);
        }
    }

    private void changeweathertxt(String w, int icon) {
        ((TextView) findViewById(R.id.weather_txt)).setText(w);
        ((ImageView) findViewById(R.id.icon_img)).setImageResource(icon);
    }

    private String getTxtfromStr(int rString) {
        return this.getResources().getString(rString);
    }


}