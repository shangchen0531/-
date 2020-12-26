package com.example.wechatimitation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class AndroidServer {
    private final String IP = "118.89.245.32";
    private final String uurl = "http://" + IP + "/AndroidServer/index2.php";
    private OkHttpClient client;
    private Gson gson;
    private Response response;
    private final Character UTF8_BOM = '\uFEFF';

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
                .add("_userName", userName)
                .add("_parameter", PARA_GET_PEO_BY_USERNAME)
                .build();

        // parseJSONWithGSON
        try {
            String result = postToServer(requestBody);
            result = removeUTF8BOM(result);
            return gson.fromJson(result, new TypeToken<List<Friend>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Msg> getMsgByTwoUserName(String selfName, String friendName) {
        RequestBody requestBody = new FormBody.Builder()
                .add("_selfName", selfName)
                .add("_friendName", friendName)
                .add("_parameter", PARA_GET_MES_BY_TWO_USERNAME)
                .build();

        // parseJSONWithGSON
        try {
            String result = postToServer(requestBody);
            result = removeUTF8BOM(result);
            return gson.fromJson(result, new TypeToken<List<Msg>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int login(String userName, String userPassword) {
        RequestBody requestBody = new FormBody.Builder()
                .add("_userName", userName)
                .add("_userPassword", userPassword)
                .add("_parameter", PARA_LOGIN)
                .build();

        // parseJSONWithJSONObject
        try {
            String result = postToServer(requestBody);
            result = removeUTF8BOM(result);
            JSONObject jsonObject = new JSONObject(result);
            int status = jsonObject.getInt("status");
            // 0: 用户未注册，-1：用户密码错误，1：用户账户和密码正确
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Boolean register(String userName, String userPass) {

        // 需要检测密码不是引号，单双都不行

        if (userPass.contains("'") || userPass.contains("\"")){
            return false;
        }

        RequestBody requestBody = new FormBody.Builder()
                .add("_userName", userName)
                .add("_userPassword", userPass)
                .add("_parameter", PARA_REGISTER)
                .build();

        try {
            String result = postToServer(requestBody);
            result = removeUTF8BOM(result);
            JSONObject jsonObject = new JSONObject(result);
            boolean status = jsonObject.getBoolean("status");
            // false: 注册失败，true：注册成功
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean addFriend(String userName, String friendName) {
        RequestBody requestBody = new FormBody.Builder()
                .add("_userName", userName)
                .add("_friendName", friendName)
                .add("_parameter", PARA_ADD_FRIEND)
                .build();

        try {
            String result = postToServer(requestBody);
            result = removeUTF8BOM(result);
            JSONObject jsonObject = new JSONObject(result);
            boolean status = jsonObject.getBoolean("status");
            // false: 添加失败，true：添加成功
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean chat(String userName, String friendName, String content) {
        RequestBody requestBody = new FormBody.Builder()
                .add("_userName", userName)
                .add("_friendName", friendName)
                .add("_content", content)
                .add("_parameter", PARA_CHAT)
                .build();

        try {
            String result = postToServer(requestBody);
            result = removeUTF8BOM(result);
            JSONObject jsonObject = new JSONObject(result);
            boolean status = jsonObject.getBoolean("status");
            // false: 发送失败，true：发送成功
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String postToServer(RequestBody body){

        try {
            Request request = new Request.Builder()
                    .url(uurl)
                    .post(body)
                    .build();

            Thread tt = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        response = client.newCall(request).execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            tt.start();
            tt.join();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String removeUTF8BOM(String s) {
        int idx = 0;
        while (s.charAt(idx) == UTF8_BOM) {
            ++idx;
        }
        return s.substring(idx);
    }
}
