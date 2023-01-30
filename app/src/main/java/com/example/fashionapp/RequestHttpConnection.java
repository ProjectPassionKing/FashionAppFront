package com.example.fashionapp;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class RequestHttpConnection {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Map<String, String> callApi(Context fcontext) throws IOException, JSONException, NoSuchAlgorithmException, KeyManagementException, CertificateException, KeyStoreException, NoSuchProviderException {

        String ydate = LocalDate.now().minusDays(1).toString().replaceAll("-", "");
        String ctime = LocalTime.now().minusHours(1).toString().substring(0, 2) + "00";

        String service_key = "byka7IIvsckHZJ1Gmr7CQMbdVSnXEjP4AFAbPrThEKFDRRCRmH9r%2FYtjgdch%2BDfTx11uQrrUp7Ukw03rATjLcw%3D%3D";
        String num_of_rows = "290";
        String page_num = "1";
        String data_type = "JSON";
        String base_time = "2300";
        String nx = "37";
        String ny = "127";

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?" +
                "serviceKey=" + service_key +
                "&pageNo=" + page_num +
                "&numOfRows=" + num_of_rows +
                "&dataType=" + data_type +
                "&base_date=" + ydate +
                "&base_time=" + base_time +
                "&nx=" + nx +
                "&ny=" + ny;

        URL req_url = new URL(url);

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream caInput = fcontext.getResources().openRawResource(R.raw.gsrsaovsslca2018);
        Certificate ca;
        try {
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

        // Tell the URLConnection to use a SocketFactory from our SSLContext
        HttpsURLConnection urlConnection =
                (HttpsURLConnection) req_url.openConnection();
        urlConnection.setSSLSocketFactory(context.getSocketFactory());
        InputStream in = urlConnection.getInputStream();

        String text = new BufferedReader(
                new InputStreamReader(in, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        urlConnection.disconnect();

        JSONObject mainObject = new JSONObject(text);
        JSONArray itemArray = mainObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");

        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < itemArray.length(); i++) {
            JSONObject item = itemArray.getJSONObject(i);
            String category = item.getString("category");
            String value = item.getString("fcstValue");
            if (item.getString("category").equals("TMN") || item.getString("category").equals("TMX")) {
                result.put(category, value);
            }
            if (item.getString("fcstTime").equals(ctime)) {
                result.put(category, value);
            }
        }
        return result;
    }
}
