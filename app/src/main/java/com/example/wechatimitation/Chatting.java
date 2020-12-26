package com.example.wechatimitation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Chatting extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>(), list2;
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private AndroidServer ad;
    private Friend me;
    private Context mContext;
    public boolean chatting = true;
    private Thread tt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        mContext = this;

        Intent data = getIntent();
        String name = data.getStringExtra("contactsName");
        int imageId = data.getIntExtra("contactsImage", R.drawable.profile);
        if (imageId == -1) {
            imageId = R.drawable.profile;
        }
        String myName = data.getStringExtra("myName");




        if (ad == null) {
            ad = new AndroidServer();
        }

        ImageView arrow = (ImageView) findViewById(R.id.arrow_btn);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView showName = (TextView) findViewById(R.id.showName);

        if (me == null) {
            me = ad.getContactsByUserName(myName).get(0);
            if (me.getUser_img() == -1) {
                me.setUser_img(R.drawable.profile);
            }
        }

        showName.setText(name);

        initMsgs(name); // 初始化聊天消息

        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new MsgAdapter(msgList, name, imageId, me.getUser_name(), me.getUser_img());
        msgRecyclerView.setLayoutManager(layoutManager);
        msgRecyclerView.setAdapter(adapter);
        msgRecyclerView.scrollToPosition(msgList.size() - 1);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!content.isEmpty()) {
                    try {
                        if(! ad.chat(myName, name, content)) {
                            Msg.showText(mContext, "发送失败");
                        }
                        else {
                            Log.d("chat: ", "发送成功");
                        }
                    } catch (Exception e) {
                        Msg.showText(mContext, "发送失败");
                        e.printStackTrace();
                    }


                    // 现在发送消息只是保存到服务器，交由线程来刷新聊天框

//                    Msg msg = new Msg(content, Msg.TYPE_SENT);
//                    msgList.add(msg);
//
//                    // 当有新消息时，刷新RecyclerView中的显示
//                    adapter.notifyItemInserted(msgList.size() - 1);
//
//                    // 将RecyclerView 定位到最后一行
//                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
//
                    // 清空输入框中的内容
                    inputText.setText("");
                }
            }
        });
        tt = new Thread(new Runnable() {
            @Override
            public void run() {
                // Looper.prepare();
                long a, b;
                a = System.currentTimeMillis();
                while (chatting) {
                    b = System.currentTimeMillis();
                    if (b - a >= 2000) { // 每 1秒刷新一次聊天框，全部交由线程来刷新，发送消息后不刷新

                        // Msg.showText(mContext, "test");
                        try {
                            list2 = ad.getMsgByTwoUserName(myName, name);
                            if (list2.size() > msgList.size()) {
                                Collections.reverse(list2);

                                msgList.clear();
                                msgList.addAll(list2);
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {

                                        // Stuff that updates the UI

//                                Msg msg = new Msg("test", Msg.TYPE_SENT);
//                                msgList.add(msg);
                                        adapter.notifyDataSetChanged();
                                        msgRecyclerView.scrollToPosition(msgList.size() - 1);

                                    }
                                });
                                // adapter.notifyItemInserted(msgList.size() - 1);
                                // msgRecyclerView.scrollToPosition(msgList.size() - 1);
                            }

//                                    if (adapter.getItemCount() != msgList.size()) {
//                                        // 当有新消息时，刷新RecyclerView中的显示
//                                        adapter = new MsgAdapter(msgList)
//                                        adapter.notifyItemInserted(msgList.size() - 1);
//
//                                        // 将RecyclerView 定位到最后一行
//                                        msgRecyclerView.scrollToPosition(msgList.size() - 1);
//                                    }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        a = b;
                        Log.d("chat", "chatting");
                    }
                }
                Log.d("chat", "finish");
            }
        });
        tt.start();
    }

    @Override
    protected void onDestroy() {
        chatting = false;
        try {
            tt.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        chatting = true;
        tt.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        chatting = false;
        try {
            tt.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initMsgs(String name) {
        msgList = ad.getMsgByTwoUserName(me.getUser_name(), name);
        if (msgList != null) {
            Collections.reverse(msgList);
        }

//        msgList.add(new Msg("你好呀", Msg.TYPE_RECEIVED));
//        msgList.add(new Msg("初次见面，我是rap king", Msg.TYPE_RECEIVED));
//        msgList.add(new Msg("你好", Msg.TYPE_SENT));
    }
}