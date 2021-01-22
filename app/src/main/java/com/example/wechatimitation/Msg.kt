package com.example.wechatimitation

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class Msg {
    @SuppressLint("SimpleDateFormat")
    private val TIME_FORMAT = SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
    private var content: String? = null
    private var type = 0
    private var timeStr: String? = null

    constructor() {}
    constructor(content: String?, type: Int) {
        this.content = content
        this.type = type
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }

    fun getType(): Int {
        return type
    }

    fun setType(type: Int) {
        this.type = type
    }

    fun getTime(): Date {
        try {
            return TIME_FORMAT.parse(timeStr)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Date()
    }

    fun getTimeStr(): String? {
        return timeStr
    }

    fun setTimeStr(timeStr: String?) {
        this.timeStr = timeStr
    }

    companion object {
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
        fun showText(parent: Context?, text: String?) {
            var tt = Toast.makeText(parent, text, Toast.LENGTH_SHORT)
            tt!!.show()
            tt = null
        }
    }
}