package com.example.fashionapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
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
import java.util.HashMap;
import java.util.Map;

public class WeatherView extends ConstraintLayout {
    private Map<String, String> result;

    public WeatherView(@NonNull Context context){
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
                result = con.callApi(context);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        weather();
                    }
                });
            } catch (IOException | JSONException | NoSuchAlgorithmException | KeyManagementException | CertificateException | KeyStoreException | NoSuchProviderException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void weather() {
        if (!result.isEmpty()){
            String tmp = result.get("TMP") + "°C"; //1시간 기온
            String tmn = result.get("TMN") + "°C"; //최저기온
            String tmx = result.get("TMX") + "°C";//최고기온
            String sky = result.get("SKY"); //하늘상태 맑음1 구름많음 3 흐림 4
            String pty = result.get("PTY"); //강수형태 없음0 비1 비/눈2 눈3 소나기4
            String pcp = result.get("PCP"); //강수량
            String pop = result.get("POP"); //강수확률 %

            ((TextView)findViewById(R.id.temperature_txt)).setText(tmp);
            ((TextView)findViewById(R.id.mtemperature_txt)).setText(tmn + "/" + tmx);
        }
    }

}
