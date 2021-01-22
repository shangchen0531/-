package com.example.wechatimitation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val intent = Intent(this, MainActivity::class.java)

        Timer().schedule(timerTask {
            startActivity(intent)
        }, 2000)

    }
}