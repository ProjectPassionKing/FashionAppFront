package com.example.fashionapp;

import android.content.ContentValues;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class RequestHttpConnection {
//    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void callApi(Context fcontext) throws IOException, JSONException, NoSuchAlgorithmException, KeyManagementException, CertificateException, KeyStoreException {
        String service_key = "byka7IIvsckHZJ1Gmr7CQMbdVSnXEjP4AFAbPrThEKFDRRCRmH9r%2FYtjgdch%2BDfTx11uQrrUp7Ukw03rATjLcw%3D%3D";
        String num_of_rows = "12";
        String page_num = "1";
        String data_type = "JSON";
        String base_date = "20230125";
        String base_time = "1100";
        String nx = "37";
        String ny = "127";

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" +
                "serviceKey=" + service_key +
                "&pageNo=" + page_num +
                "&numOfRows=" + num_of_rows +
                "&dataType=" + data_type +
                "&base_date=" + base_date +
                "&base_time=" + base_time +
                "&nx=" + nx +
                "&ny=" + ny;

        URL req_url = new URL(url);

//        System.out.println("Response code: " + connection.getResponseCode());
//        BufferedReader rd;
//
//        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        connection.disconnect();
//        System.out.println(sb.toString());

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream caInput = fcontext.getResources().openRawResource(R.raw.gsrsaovsslca2018);
        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
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

        // Tell the URLConnection to use a SocketFactory from our SSLContext
        HttpsURLConnection urlConnection =
                (HttpsURLConnection) req_url.openConnection();
        urlConnection.setSSLSocketFactory(context.getSocketFactory());
        InputStream in = urlConnection.getInputStream();

        String text = new BufferedReader(
                new InputStreamReader(in, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//        if (urlConnection.getResponseCode() == 200){
//            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//        }else{
//            //error
//        }
//
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while((line = bufferedReader.readLine())!=null){
//            sb.append(line);
//        }
//        bufferedReader.close();
//        String result = sb.toString();
        urlConnection.disconnect();
//
//        JSONObject mainObject = new JSONObject(result);
////        JSONArray mainObject = new JSONArray(result);
//        JSONArray itemArray = mainObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
//
//        for(int i=0;i< itemArray.length();i++){
//            JSONObject item = itemArray.getJSONObject(i);
//            String category = item.getString("category");
//            String value = item.getString("fcstValue");
//            System.out.println(category+"" + value);
//        }
//    }
    }
}
