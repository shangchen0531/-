package com.example.wechatimitation;

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

    private final static String PARA_GET_PEO_BY_USERNAME = "10086";
    private final static String PARA_GET_MES_BY_USERNAME = "10087";
    private final static String PARA_LOGIN = "10088";
    private final static String PARA_REGISTER = "10089";
    private final static String PARA_ADD_FRIEND = "10090";
    private final static String PARA_CHAT = "10091";

    public AndroidServer() {
        client = new OkHttpClient();
    }

    public List<Contacter> getContactsByUserName(String userName) {
        RequestBody requestBody = new FormBody.Builder()
                .add("_username", userName)
                .add("_parameter", PARA_GET_PEO_BY_USERNAME)
                .build();
        String result = postToServer(requestBody);
        List<Contacter> ls = new ArrayList<>();
        return null;
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
