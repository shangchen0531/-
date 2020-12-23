package com.example.wechatimitation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String userID = "123";
    private String userPW = "123";

//    TextView responseText;
//    private final String IP = "118.89.245.32";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t = (TextView) this.findViewById(R.id.user_mark);
        final EditText e = (EditText) this.findViewById(R.id.user_text);
        setHintTextSize(e, "微信号/手机号/QQ号", (int)t.getTextSize() - 10);

        TextView t2 = (TextView) this.findViewById(R.id.pw_mark);
        final EditText e2 = (EditText) this.findViewById(R.id.pw_text);
        setHintTextSize(e2, "请填写密码", (int)t2.getTextSize() - 10);

        TextView cancel = (TextView) this.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIDText = e.getText().toString();
                String userPwText = e2.getText().toString();
                if (userID == null) {
                    msg("账号未注册");
                    Intent register_intent = new Intent(MainActivity.this, Register.class);
                    startActivityForResult(register_intent, 1);
                } else if (userIDText.isEmpty() && userPwText.isEmpty()) {
                    msg("请输入账号和密码");
                } else if (userIDText.isEmpty()) {
                    msg("请输入账号");
                } else if (userPwText.isEmpty()) {
                    msg("请输入密码");
                } else if (userIDText.compareTo(userID) == 0 && userPwText.compareTo(userPW) == 0) {
//                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    Intent contact = new Intent(MainActivity.this, recent_message.class);
                    startActivity(contact);

                } else {
                    msg("账号或密码错误，无法登录");
                }
            }
        });


//        Button sendRequest = (Button) findViewById(R.id.btn_test);
//        responseText = (TextView) findViewById(R.id.result_test);
//        sendRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == R.id.btn_test) {
//                    sendRequestWithHttpURLConnection();
//                }
//            }
//        });
    }

//    private void sendRequestWithHttpURLConnection() {
//        // 开启线程来发起网络请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection = null;
//                BufferedReader reader = null;
//                try {
//                    URL url = new URL("http://" + IP + "/AndroidServer/index.php");
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    InputStream in = connection.getInputStream();
//
//                    // 下面对获取到的输入流进行读取
//                    reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//                    showResponse(response.toString());
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (reader != null) {
//                        try {
//                            reader.close();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }

//    private void showResponse(final String response) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                // 在这里进行UI操作，将结果显示到界面上
//                responseText.setText(response);
//            }
//        });
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String userID_got = data.getStringExtra("userID");
                    String userPw_got = data.getStringExtra("userPw");
                    userID = userID_got;
                    userPW = userPw_got;
                    msg("注册成功, 请再次输入账号密码登录");
                }
                break;
            default:
        }
    }

    /**
     * 设置 hint字体大小
     * @param editText 输入控件;
     * @param hintText hint的文本内容
     * @param textSize hint的文本的文字大小（以px为单位设置即可）
     */

    private void setHintTextSize(EditText editText, String hintText, int textSize) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(hintText);
        // 新建一个属性对象，设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(textSize);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        editText.setHint(new SpannableString(ss)); // 一定要进行转换，否则属性会消失
    }

    /**
     * Toast显示消息
     * @param m 欲打印的消息;
     */

    private void msg(String m) {
        Toast tt = Toast.makeText(MainActivity.this, m, Toast.LENGTH_SHORT);
        tt.show();
        tt = null;
    }
}