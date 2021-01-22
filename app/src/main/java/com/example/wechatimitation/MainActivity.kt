package com.example.wechatimitation

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //    private String userID = null;
    //    private String userPW = "123";
    private var ad: AndroidServer? = null

    //    TextView responseText;
    //    private final String IP = "118.89.245.32";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ad == null) {
            ad = AndroidServer()
        }

        // JSONObject js = new JSONObject("\uFEFF\uFEFF\uFEFF\uFEFF\uFEFF{'status':-1}")
        val t = findViewById<View>(R.id.user_mark) as TextView
        val e = findViewById<View>(R.id.user_text) as EditText
        setHintTextSize(e, "微信号/手机号/QQ号", t.textSize.toInt() - 10)
        val t2 = findViewById<View>(R.id.pw_mark) as TextView
        val e2 = findViewById<View>(R.id.pw_text) as EditText
        setHintTextSize(e2, "请填写密码", t2.textSize.toInt() - 10)
        val cancel = findViewById<View>(R.id.btn_cancel) as TextView
        cancel.setOnClickListener { finish() }
        val register = findViewById<View>(R.id.btn_register) as TextView
        register.setOnClickListener {
            val register_intent = Intent(this@MainActivity, Register::class.java)
            startActivity(register_intent)
        }
        val login = findViewById<View>(R.id.login) as Button
        login.setOnClickListener {
            val userIDText = e.text.toString()
            val userPwText = e2.text.toString()
            if (userPwText.contains("'") || userPwText.contains("\"")) {
                Msg.Companion.showText(this@MainActivity, "密码内不要包含引号")
            } else {
                val status = ad!!.login(userIDText, userPwText)
                when (status) {
                    0 -> Msg.Companion.showText(this@MainActivity, "用户不存在")
                    -1 -> Msg.Companion.showText(this@MainActivity, "用户密码错误")
                    1 -> {
                        Msg.Companion.showText(this@MainActivity, "登录成功")

                        //List<Msg> mm = ad.getMsgByTwoUserName(userIDText, "10087");
                        val contact = Intent(this@MainActivity, recent_message::class.java)
                        contact.putExtra("userName", userIDText)
                        startActivity(contact)
                    }
                    else -> Msg.Companion.showText(this@MainActivity, "未知错误")
                }
            }

//                if (userID == null) {
//                    msg("账号未注册");
//                    Intent register_intent = new Intent(MainActivity.this, Register.class);
//                    startActivityForResult(register_intent, 1);
//                } else if (userIDText.isEmpty() && userPwText.isEmpty()) {
//                    msg("请输入账号和密码");
//                } else if (userIDText.isEmpty()) {
//                    msg("请输入账号");
//                } else if (userPwText.isEmpty()) {
//                    msg("请输入密码");
//                } else if (userIDText.compareTo(userID) == 0 && userPwText.compareTo(userPW) == 0) {
////                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//
//                    Intent contact = new Intent(MainActivity.this, recent_message.class);
//                    startActivity(contact);
//
//                } else {
//                    msg("账号或密码错误，无法登录");
//                }
        }


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
    //    @Override
    //    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //        super.onActivityResult(requestCode, resultCode, data);
    //        switch (requestCode) {
    //            case 1:
    //                if (resultCode == RESULT_OK) {
    //                    String userID_got = data.getStringExtra("userID");
    //                    String userPw_got = data.getStringExtra("userPw");
    //                    userID = userID_got;
    //                    userPW = userPw_got;
    //                    msg("注册成功, 请再次输入账号密码登录");
    //                }
    //                break;
    //            default:
    //        }
    //    }
    /**
     * 设置 hint字体大小
     * @param editText 输入控件;
     * @param hintText hint的文本内容
     * @param textSize hint的文本的文字大小（以px为单位设置即可）
     */
    private fun setHintTextSize(editText: EditText, hintText: String, textSize: Int) {
        // 新建一个可以添加属性的文本对象
        val ss = SpannableString(hintText)
        // 新建一个属性对象，设置文字的大小
        val ass = AbsoluteSizeSpan(textSize)
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        // 设置hint
        editText.hint = SpannableString(ss) // 一定要进行转换，否则属性会消失
    }
}