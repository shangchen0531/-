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
    private String skycon;  // 天气情况
    private double temperature; // 温度


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
                    // Log.d("weather", resposeData);
                    JSONObject object = new JSONObject(resposeData);
                    longitude = object.getDouble("lon");
                    latitude = object.getDouble("lat");
                    // Log.d("lat", object.getDouble("lat") + "");
                    // Log.d("lon", object.getDouble("lon") + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tt.start();
        tt.join();

        // Log.d("lat", latitude + "");
        // Log.d("lon", longitude + "");

            /**
            *{
            *    "status": "success",
            *    "country": "China",
            *    "countryCode": "CN",
            *    "region": "GD",
            *    "regionName": "Guangdong",
            *    "city": "Foshan",
            *    "zip": "",
            *    "lat": 23.0215,
            *    "lon": 113.121,
            *    "timezone": "Asia/Shanghai",
            *    "isp": "CHINANET Guangdong province Foshan MAN network",
            *    "org": "Chinanet GD",
            *    "as": "AS136200 CHINANET Guangdong province Foshan MAN network",
            *    "query": "59.38.249.133"
            *}
             */

        // https://api.caiyunapp.com/v2.5/{token}/{经度},{纬度}/realtime.json
        Thread tt2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://api.caiyunapp.com/v2.5/" + token + "/" + longitude + "," + latitude + "/realtime.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String resposeData = response.body().string();

                    JSONObject object = new JSONObject(resposeData);

                    String result = object.getJSONObject("result")
                            .getJSONObject("realtime")
                            .getString("result");
                    if (! result.equals("ok")) {
                        skycon = "未知";
                        temperature = -1;
                    } else {
                        skycon = object.getJSONObject("result")
                                .getJSONObject("realtime")
                                .getString("skycon");
                        temperature = object.getJSONObject("result")
                                .getJSONObject("realtime")
                                .getDouble("temperature");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        tt2.start();
        tt2.join();
        skycon = dealWeather(skycon);

    }

    private String dealWeather(String weather) {
        switch (weather) {
            case "CLEAR_DAY":
                return "晴（白天）";
            case "CLEAR_NIGHT":
                return "晴（夜间）";
            case "PARTLY_CLOUDY_DAY":
                return "多云（白天）";
            case "PARTLY_CLOUDY_NIGHT":
                return "多云（夜间）";
            case "CLOUDY":
                return "阴";
            case "LIGHT_HAZE":
                return "轻度雾霾";
            case "MODERATE_HAZE":
                return "中度雾霾";
            case "HEAVY_HAZE":
                return "重度雾霾";
            case "LIGHT_RAIN":
                return "小雨";
            case "MODERATE_RAIN":
                return "中雨";
            case "HEAVY_RAIN":
                return "大雨";
            case "STORM_RAIN":
                return "暴雨";
            case "FOG":
                return "雾";
            case "LIGHT_SNOW":
                return "小雪";
            case "MODERATE_SNOW":
                return "中雪";
            case "HEAVY_SNOW":
                return "大雪";
            case "STORM_SNOW":
                return "暴雪";
            case "DUST":
                return "浮尘";
            case "SAND":
                return "沙尘";
            case "WIND":
                return "大风";
            default:
                return "未知";
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getSkycon() {
        return skycon;
    }

//    public List<Friend> getContactsByUserName(String userName) {
//        RequestBody requestBody = null;
//        String result = postToServer(requestBody);
//
//        // parseJSONWithGSON
//        return gson.fromJson(result, new TypeToken<List<Friend>>(){}.getType());
//    }

}
