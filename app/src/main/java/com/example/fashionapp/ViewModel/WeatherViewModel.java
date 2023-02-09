package com.example.fashionapp.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fashionapp.BuildConfig;
import com.example.fashionapp.Model.Entity.WeatherItem;
import com.example.fashionapp.Model.Entity.WeatherResponse;
import com.example.fashionapp.Model.Entity.WeatherResult;
import com.example.fashionapp.Model.Weather;
import com.example.fashionapp.Model.WeatherAPI;
import com.example.fashionapp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WeatherViewModel extends AndroidViewModel {
    private MutableLiveData<Weather> lweather = new MutableLiveData<>();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Weather> getData(){
        return lweather;
    }

    public void loadData(Context fcontext) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        String ydate = null;
        String ctime= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ydate = LocalDate.now().minusDays(1).toString().replaceAll("-", "");
            ctime = LocalTime.now().minusHours(1).toString().substring(0, 2) + "00";
        }

        String num_of_rows = "290";
        String page_num = "1";
        String data_type = "JSON";
        String base_time = "2300";
        String nx = "37";
        String ny = "127";

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/";

        InputStream caInput = fcontext.getResources().openRawResource(R.raw.gsrsaovsslca2018);
        Certificate ca;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            ca = cf.generateCertificate(caInput);
        } finally {
            caInput.close();
        }

        // Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // Create an SSLContext that uses our TrustManager
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);

        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(context.getSocketFactory(), (X509TrustManager) tmf.getTrustManagers()[0])
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        Call<WeatherResult> call = weatherAPI.weatherRes(nx, ny, ydate, base_time, data_type, page_num, num_of_rows);

        Weather weather = new Weather();

        String finalCtime = ctime;

        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {

                if (response.isSuccessful()){
                    WeatherResult wresult = response.body();

                    List<WeatherItem> items = wresult.getWeatherResponse().getWeatherBody().getItmes().getItem();
                    for(WeatherItem i: items){
                        String category = i.getCategory();
                        String value = i.getFcstValue();
                        if (i.getFcstTime().equals("0600") && category.equals("TMN")) {
                            weather.setMinTmp(value);
                        }
                        if(i.getFcstTime().equals("1500") && category.equals("TMX")){
                            weather.setMaxTmp(value);
                        }
                        if (i.getFcstTime().equals(finalCtime)) {
                            if (category.equals("TMP")) weather.setTmp(value);
                            if (category.equals("SKY")) weather.setSky(value);
                            if (category.equals("PTY")) weather.setRain(value);
                            if (category.equals("PCP")) weather.setRainper(value);
                            if (category.equals("SNO")) weather.setSnow(value);
                        }
                    }
                    lweather.postValue(weather);
                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {

                System.out.println(t.getMessage());
            }
        });

    }

}


