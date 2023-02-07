package com.example.fashionapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import org.json.JSONException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.Map;

public class WeatherView extends ConstraintLayout {
    public WeatherView(@NonNull Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.weather, this, true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void connectapi(Context context, Activity activity) throws InterruptedException {
        new Thread(() ->
        {
            RequestHttpConnection con = new RequestHttpConnection();
            try {
//                Map<String, String> result = con.callApi(context);
                Weather result = con.callApi(context);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setWeatherUI(result);
//                        weather(result);
                    }
                });
            } catch (IOException | JSONException | NoSuchAlgorithmException | KeyManagementException | CertificateException | KeyStoreException | NoSuchProviderException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private void setWeatherUI(Weather weather){
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

    private void weather(Map<String, String> result) {
        String c = getTxtfromStr(R.string.temperature);
        if (!result.isEmpty()) {
            String tmp = result.get("TMP") + c; //1시간 기온
            String tmn = result.get("TMN") + c; //최저기온
            String tmx = result.get("TMX") + c;//최고기온
            String sky = result.get("SKY"); //하늘상태 맑음1 구름많음 3 흐림 4
            String pty = result.get("PTY"); //강수형태 없음0 비1 비/눈2 눈3 소나기4
            String pcp = "강수량: " + result.get("PCP") + "mm"; //강수량
//            String pop = result.get("POP"); //강수확률 %
            String sno = result.get("SNO"); //신적설 량 (cm)

            if (!sno.equals("적설없응")){
                skycondition(sky, pty, sno);
            }
            skycondition(sky, pty, pcp);

            ((TextView) findViewById(R.id.temperature_txt)).setText(tmp);
            ((TextView) findViewById(R.id.mtemperature_txt)).setText(tmn + "/" + tmx);
        }
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
