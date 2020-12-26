package com.example.wechatimitation;

import java.util.Date;

public class Recent_Msg {

//    private Peo recent_peo;
    private Friend recent_peo;
    private Date recent_time;
    private Msg recent_msg;

//    Recent_Msg(Peo peo, Msg msg, Date date) {
//        this.recent_peo = peo;
//        this.recent_msg = msg;
//        this.recent_time = date;
//    }

    Recent_Msg(Friend peo, Msg msg, Date date) {
        this.recent_peo = peo;
        this.recent_msg = msg;
        this.recent_time = date;
    }

//    public Peo getRecent_peo() {
//        return recent_peo;
//    }

//    public void setRecent_peo(Peo recent_peo) {
//        this.recent_peo = recent_peo;
//    }

    public Friend getRecent_peo() {
        return recent_peo;
    }

    public void setRecent_peo(Friend recent_peo) {
        this.recent_peo = recent_peo;
    }

    public Date getRecent_time() {
        return recent_time;
    }

    public void setRecent_time(Date recent_time) {
        this.recent_time = recent_time;
    }

    public Msg getRecent_msg() {
        return recent_msg;
    }

    public void setRecent_msg(Msg recent_msg) {
        this.recent_msg = recent_msg;
    }
}
