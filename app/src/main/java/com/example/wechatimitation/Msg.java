package com.example.wechatimitation;

import android.content.Context;
import android.widget.Toast;

public class Msg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int type;

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

    public static void showText(Context parent, String text) {
        Toast tt = Toast.makeText(parent, text, Toast.LENGTH_SHORT);
        tt.show();
        tt = null;
    }

}
