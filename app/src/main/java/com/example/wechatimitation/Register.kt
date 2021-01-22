package com.example.wechatimitation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wechatimitation.Register

class Register : AppCompatActivity() {
    private var ad: AndroidServer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        if (ad == null) {
            ad = AndroidServer()
        }
        val btn_return = findViewById<View>(R.id.btn_return) as TextView
        btn_return.setOnClickListener { finish() }
        val register = findViewById<View>(R.id.register_btn) as Button
        register.setOnClickListener {
            val userId_register = findViewById<View>(R.id.userID_register) as EditText
            val userPw_register = findViewById<View>(R.id.userPw_register) as EditText
            val userPw_sure_register = findViewById<View>(R.id.userPw_sure_register) as EditText
            val userID = userId_register.text.toString()
            val userPw = userPw_register.text.toString()
            val userPw_sure = userPw_sure_register.text.toString()
            if (userID.isEmpty()) {
                Msg.Companion.showText(this@Register, "账号不能为空")
            } else if (userPw.isEmpty()) {
                Msg.Companion.showText(this@Register, "密码不能为空")
            } else if (userPw_sure.isEmpty()) {
                Msg.Companion.showText(this@Register, "请再次输入密码")
            } else {
                if (userPw.compareTo(userPw_sure) == 0) {
                    val status = ad!!.register(userID, userPw)
                    Msg.Companion.showText(this@Register, if (status!!) "成功" else "失败")
                } else {
                    Msg.Companion.showText(this@Register, "两次输入密码不一致")
                }
            }
        }
    }

    private fun msg(m: String) {
        var tt = Toast.makeText(this@Register, m, Toast.LENGTH_SHORT)
        tt!!.show()
        tt = null
    }
}