package com.example.wechatimitation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class Register extends AppCompatActivity {

    private AndroidServer ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        if (ad == null) {
            ad = new AndroidServer();
        }

        TextView btn_return = (TextView) findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button register = (Button) findViewById(R.id.register_btn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userId_register = (EditText) findViewById(R.id.userID_register);
                EditText userPw_register = (EditText) findViewById(R.id.userPw_register);
                EditText userPw_sure_register = (EditText) findViewById(R.id.userPw_sure_register);

                String userID = userId_register.getText().toString();
                String userPw = userPw_register.getText().toString();
                String userPw_sure = userPw_sure_register.getText().toString();

                if (userID.isEmpty()) {
                    Msg.showText(Register.this, "账号不能为空");
                } else if (userPw.isEmpty()) {
                    Msg.showText(Register.this, "密码不能为空");
                } else if (userPw_sure.isEmpty()) {
                    Msg.showText(Register.this, "请再次输入密码");
                } else {
                    if (userPw.compareTo(userPw_sure) == 0) {
                        Boolean status = ad.register(userID, userPw);
                        Msg.showText(Register.this, (status ? "成功" : "失败"));
                    }
                    else {
                        Msg.showText(Register.this, "两次输入密码不一致");
                    }
                }
            }
        });

    }

    private void msg(String m) {
        Toast tt = Toast.makeText(Register.this, m, Toast.LENGTH_SHORT);
        tt.show();
        tt = null;
    }
}