package com.example.wechatimitation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    private String content;
    private int type;
    private String timeStr;

    public Msg(){}

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getTime() {
        try {
            return TIME_FORMAT.parse(timeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public static void showText(Context parent, String text) {
        Toast tt = Toast.makeText(parent, text, Toast.LENGTH_SHORT);
        tt.show();
        tt = null;
    }

}
