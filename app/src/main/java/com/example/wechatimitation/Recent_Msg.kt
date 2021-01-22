package com.example.wechatimitation

import java.util.*

class Recent_Msg //    Recent_Msg(Peo peo, Msg msg, Date date) {
//        this.recent_peo = peo;
//        this.recent_msg = msg;
//        this.recent_time = date;
//    }
internal constructor(//    private Peo recent_peo;
        private var recent_peo: Friend?, private var recent_msg: Msg?, private var recent_time: Date?) {

    //    public Peo getRecent_peo() {
    //        return recent_peo;
    //    }
    //    public void setRecent_peo(Peo recent_peo) {
    //        this.recent_peo = recent_peo;
    //    }
    fun getRecent_peo(): Friend? {
        return recent_peo
    }

    fun setRecent_peo(recent_peo: Friend?) {
        this.recent_peo = recent_peo
    }

    fun getRecent_time(): Date? {
        return recent_time
    }

    fun setRecent_time(recent_time: Date?) {
        this.recent_time = recent_time
    }

    fun getRecent_msg(): Msg? {
        return recent_msg
    }

    fun setRecent_msg(recent_msg: Msg?) {
        this.recent_msg = recent_msg
    }

}