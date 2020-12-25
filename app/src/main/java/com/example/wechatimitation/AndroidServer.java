package com.example.wechatimitation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class AndroidServer {
    private final String IP = "118.89.245.32";
    private final String uurl = "http://" + IP + "/AndroidServer/index.php";
    private final OkHttpClient client;
    private final Gson gson;

    private final static String PARA_GET_PEO_BY_USERNAME = "10086";
    private final static String PARA_GET_MES_BY_TWO_USERNAME = "10087";
    private final static String PARA_LOGIN = "10088";
    private final static String PARA_REGISTER = "10089";
    private final static String PARA_ADD_FRIEND = "10090";
    private final static String PARA_CHAT = "10091";

    public AndroidServer() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public List<Friend> getContactsByUserName(String userName) {
        RequestBody requestBody = new FormBody.Builder()
                .add("_username", userName)
                .add("_parameter", PARA_GET_PEO_BY_USERNAME)
                .build();
        String result = postToServer(requestBody);

        // parseJSONWithGSON
        return gson.fromJson(result, new TypeToken<List<Friend>>(){}.getType());
    }

    public List<Msg> getMsgByTwoUserName(String selfName, String friendName) {
        RequestBody requestBody = new FormBody.Builder()
                .add("_selfName", selfName)
                .add("_friendName", friendName)
                .add("_parameter", PARA_GET_MES_BY_TWO_USERNAME)
                .build();
        String result = postToServer(requestBody);

        // parseJSONWithGSON
        return gson.fromJson(result, new TypeToken<List<Msg>>(){}.getType());
    }

    public int login(String userName, String userPassword) {
        RequestBody requestBody = new FormBody.Builder()
                .add("_userName", userName)
                .add("_userPassword", userPassword)
                .add("_parameter", PARA_LOGIN)
                .build();
        String result = postToServer(requestBody);

        // parseJSONWithJSONObject
        try {
            JSONObject jsonObject = new JSONObject(result);
            int status = jsonObject.getInt("status");
            // 0: 用户未注册，-1：用户密码错误，1：用户账户和密码正确
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String postToServer(RequestBody body){

        try {
            Request request = new Request.Builder()
                    .url(uurl)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
