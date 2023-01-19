package com.example.fashionapp;

import android.content.ContentValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class RequestHttpConnection {

    public void callApi() throws IOException, JSONException {
        String service_key  = "byka7IIvsckHZJ1Gmr7CQMbdVSnXEjP4AFAbPrThEKFDRRCRmH9r%2FYtjgdch%2BDfTx11uQrrUp7Ukw03rATjLcw%3D%3D";
        String num_of_rows = "10";
        String page_num = "1";
        String data_type = "JSON";
        String base_date = "20231119";
        String base_time = "1800";
        String nx = "37.492081";
        String ny = "126.926039";
        String endpoint = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0";

        String url = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?" +
                "serviceKey="+service_key+
                "&numOfRows="+num_of_rows+
                "&pageNo="+page_num+
                "&dataType="+data_type+
                "&base_date="+base_date+
                "&base_time="+base_time+
                "&nx="+nx+
                "&ny="+ny;

        URL req_url = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) req_url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader bufferedReader = null;

        if (connection.getResponseCode() == 200){
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }else{
            //error
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while((line = bufferedReader.readLine())!=null){
            sb.append(line);
        }
        bufferedReader.close();
        String result = sb.toString();
        connection.disconnect();

        JSONObject mainObject = new JSONObject(result);
//        JSONArray mainObject = new JSONArray(result);
        JSONArray itemArray = mainObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");

        for(int i=0;i< itemArray.length();i++){
            JSONObject item = itemArray.getJSONObject(i);
            String category = item.getString("category");
            String value = item.getString("fcstValue");
            System.out.println(category+"" + value);
        }
    }

    public String request(String _url, ContentValues _params){
        HttpURLConnection urlcon = null;

        StringBuffer sbParams = new StringBuffer();

        //보낼 데이터 x -> 파라미터 비움
        if (_params == null){
            sbParams.append("");
        }else{
            boolean isAnd = false;
            String key;
            String value;

            for (Map.Entry<String, Object> parameter : _params.valueSet()){
                key = parameter.getKey();
                value = parameter.getValue().toString();

                if (isAnd) sbParams.append("&");

                sbParams.append(key).append("=").append(value);

                if (!isAnd && _params.size()>=2) isAnd = true;
            }
        }

        //HttpURLConnection을 통해 web의 데이터
        try{
            URL url = new URL(_url);
            urlcon = (HttpURLConnection) url.openConnection();

            urlcon.setRequestMethod("GET");
            urlcon.setRequestProperty("Accept-Charset", "UTF-8");
            urlcon.setDoOutput(false);

            if (urlcon.getResponseCode() != HttpURLConnection.HTTP_OK){
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));

            String line;
            String page= "";

            while ((line = reader.readLine())!=null){
                page += line;
            }

            return page;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlcon!=null) urlcon.disconnect();
        }

        return null;
    }
}
