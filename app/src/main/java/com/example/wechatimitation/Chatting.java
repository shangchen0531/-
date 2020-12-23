package com.example.wechatimitation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Chatting extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        ImageView arrow = (ImageView) findViewById(R.id.arrow_btn);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView showName = (TextView) findViewById(R.id.showName);

        Intent data = getIntent();
        String name = data.getStringExtra("contactsName");
        int imageId = data.getIntExtra("contactsImage", 0);

        showName.setText(name);

        initMsgs(); // 初始化聊天消息

        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new MsgAdapter(msgList, name, imageId);
        msgRecyclerView.setLayoutManager(layoutManager);
        msgRecyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!content.isEmpty()) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);

                    // 当有新消息时，刷新RecyclerView中的显示
                    adapter.notifyItemInserted(msgList.size() - 1);

                    // 将RecyclerView 定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);

                    // 清空输入框中的内容
                    inputText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        msgList.add(new Msg("你好呀", Msg.TYPE_RECEIVED));
        msgList.add(new Msg("初次见面，我是rap king", Msg.TYPE_RECEIVED));
        msgList.add(new Msg("你好", Msg.TYPE_SENT));
    }
}