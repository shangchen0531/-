package com.example.wechatimitation;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.net.URL;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Weather {

    private final String token = "RvkRaE06QE8z5Yjp";
    private double longitude; // 经度
    private double latitude; // 纬度

    public Weather() throws InterruptedException {

        Thread tt = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://ip-api.com/json/")
                            .build();
                    Response response = client.newCall(request).execute();
                    String resposeData = response.body().string();
                    Log.d("weather", resposeData);
                    JSONObject object = new JSONObject(resposeData);
                    longitude = object.getDouble("lon");
                    latitude = object.getDouble("lat");
                    Log.d("lat", object.getDouble("lat") + "");
                    Log.d("lon", object.getDouble("lon") + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tt.start();
        tt.join();

        Log.d("lat", latitude + "");
        Log.d("lon", longitude + "");
            /**
            {
                "status": "success",
                "country": "China",
                "countryCode": "CN",
                "region": "GD",
                "regionName": "Guangdong",
                "city": "Foshan",
                "zip": "",
                "lat": 23.0215,
                "lon": 113.121,
                "timezone": "Asia/Shanghai",
                "isp": "CHINANET Guangdong province Foshan MAN network",
                "org": "Chinanet GD",
                "as": "AS136200 CHINANET Guangdong province Foshan MAN network",
                "query": "59.38.249.133"
            } **/



    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

//    public List<Friend> getContactsByUserName(String userName) {
//        RequestBody requestBody = null;
//        String result = postToServer(requestBody);
//
//        // parseJSONWithGSON
//        return gson.fromJson(result, new TypeToken<List<Friend>>(){}.getType());
//    }

}
