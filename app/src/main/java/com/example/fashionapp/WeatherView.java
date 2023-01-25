package com.example.fashionapp;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.json.JSONException;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class WeatherView extends ConstraintLayout {
    Map<String, String> result = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public WeatherView(@NonNull Context context, Map<String, String> result) throws JSONException, CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, KeyManagementException {
        super(context);
        init(context);
        weather(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.weather, this, true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void weather(Context context) throws JSONException, CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, KeyManagementException {

        new Thread(()->
        {
            try {
//                binding.weatherApi.addView(new WeatherView(getContext()));
                RequestHttpConnection con = new RequestHttpConnection();
                result = con.callApi(this.getContext());

            } catch (JSONException | CertificateException | IOException | NoSuchAlgorithmException | KeyStoreException | NoSuchProviderException | KeyManagementException e) {
                e.printStackTrace();
            }

        }).start();
        String tmp = result.get("TMP") + "°C"; //1시간 기온
        String tmn = result.get("TMN") + "°C"; //최저기온
        String tmx = result.get("TMX") + "°C";//최고기온
        String sky = result.get("SKY"); //하늘상태 맑음1 구름많음 3 흐림 4
        String pty = result.get("PTY"); //강수형태 없음0 비1 비/눈2 눈3 소나기4
        String pcp = result.get("PCP"); //강수량
        String pop = result.get("POP"); //강수확률 %
        ((TextView) findViewById(R.id.temperature_txt)).setText(tmp);
        ((TextView) findViewById(R.id.mtemperature_txt)).setText(tmn + "/" + tmx);

    }
}
