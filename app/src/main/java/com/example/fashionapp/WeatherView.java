package com.example.fashionapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
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
    private Map<String, String> result;

    public Map<String, String> getResult() {
        return result;
    }
    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public WeatherView(@NonNull Context context){
        super(context);
        result = new HashMap<>();
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

//    @SuppressLint("NewApi")
//    private void asyncapi(Context context){
//
//        callapi(context);
//
//        //onPreExecute(task 시작 전 실행될 코드 여기에 작성)
//        //api 호출
//
//        backgroundTask = Observable.fromCallable(() -> {
//                    //doInBackground(task에서 실행할 코드 여기에 작성)
//                    weather();
//
//                    return true;
//
//                }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers
//                        .mainThread()).subscribe((result)-> {
//                    //onPostExecute(task 끝난 후 실행될 코드 여기에 작성)
////                    weather();
//                    init(context);
//
//                    backgroundTask.dispose();
//                });
//    }

}
