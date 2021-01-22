package com.example.wechatimitation

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class Weather {
    private val token = "RvkRaE06QE8z5Yjp"
    private val longitude = 113.763 // 经度
    private val latitude = 23.379 // 纬度
    private var skycon : String = "" // 天气情况
    private var temperature = 0.0 // 温度 = 0.0

    private fun dealWeather(weather: String): String {
        return when (weather) {
            "CLEAR_DAY" -> "晴（白天）"
            "CLEAR_NIGHT" -> "晴（夜间）"
            "PARTLY_CLOUDY_DAY" -> "多云（白天）"
            "PARTLY_CLOUDY_NIGHT" -> "多云（夜间）"
            "CLOUDY" -> "阴"
            "LIGHT_HAZE" -> "轻度雾霾"
            "MODERATE_HAZE" -> "中度雾霾"
            "HEAVY_HAZE" -> "重度雾霾"
            "LIGHT_RAIN" -> "小雨"
            "MODERATE_RAIN" -> "中雨"
            "HEAVY_RAIN" -> "大雨"
            "STORM_RAIN" -> "暴雨"
            "FOG" -> "雾"
            "LIGHT_SNOW" -> "小雪"
            "MODERATE_SNOW" -> "中雪"
            "HEAVY_SNOW" -> "大雪"
            "STORM_SNOW" -> "暴雪"
            "DUST" -> "浮尘"
            "SAND" -> "沙尘"
            "WIND" -> "大风"
            else -> "未知"
        }
    }

    fun getTemperature(): Double {
        return temperature
    }

    fun getLongitude(): Double {
        return longitude
    }

    fun getLatitude(): Double {
        return latitude
    }

    fun getSkycon(): String {
        return skycon
    } //    public List<Friend> getContactsByUserName(String userName) {

    //        RequestBody requestBody = null;
    //        String result = postToServer(requestBody);
    //
    //        // parseJSONWithGSON
    //        return gson.fromJson(result, new TypeToken<List<Friend>>(){}.getType());
    //    }
    init {

//        Thread tt = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url("http://ip-api.com/json/")
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String resposeData = response.body().string();
//                    // Log.d("weather", resposeData);
//                    JSONObject object = new JSONObject(resposeData);
//                    longitude = object.getDouble("lon");
//                    latitude = object.getDouble("lat");
//                    // Log.d("lat", object.getDouble("lat") + "");
//                    // Log.d("lon", object.getDouble("lon") + "");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        tt.start();
//        tt.join();

        // Log.d("lat", latitude + "");
        // Log.d("lon", longitude + "");
        /**
         * {
         * "status": "success",
         * "country": "China",
         * "countryCode": "CN",
         * "region": "GD",
         * "regionName": "Guangdong",
         * "city": "Foshan",
         * "zip": "",
         * "lat": 23.0215,
         * "lon": 113.121,
         * "timezone": "Asia/Shanghai",
         * "isp": "CHINANET Guangdong province Foshan MAN network",
         * "org": "Chinanet GD",
         * "as": "AS136200 CHINANET Guangdong province Foshan MAN network",
         * "query": "59.38.249.133"
         * }
         */

        // https://api.caiyunapp.com/v2.5/{token}/{经度},{纬度}/realtime.json
        val tt2 = Thread(Runnable {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                        .url("https://api.caiyunapp.com/v2.5/$token/$longitude,$latitude/realtime.json")
                        .build()
                val response = client.newCall(request).execute()
                val resposeData = response.body!!.string()
                val `object` = JSONObject(resposeData)
                Log.d("result", resposeData)
                val result = `object`.getJSONObject("result")
                        .getJSONObject("realtime")
                        .getString("status")
                Log.d("weather_status", result)
                if (result != "ok") {
                    skycon = "未知"
                    temperature = -1.0
                } else {
                    skycon = `object`.getJSONObject("result")
                            .getJSONObject("realtime")
                            .getString("skycon")
                    temperature = `object`.getJSONObject("result")
                            .getJSONObject("realtime")
                            .getDouble("temperature")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        tt2.start()
        tt2.join()
        skycon = dealWeather(skycon)
    }
}