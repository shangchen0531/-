package com.example.wechatimitation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class recent_message extends AppCompatActivity {

    private List<Recent_Msg> recentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_message);

        initRects();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recent_message_recyclerList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecentAdapter(recentList));

    }

    private void initRects() {
        recentList.add(new Recent_Msg(
                new Peo("朱一龙", R.drawable.n1),
                new Msg("test2 msg", Msg.TYPE_SENT),
                new Date(2020 - 1900, 11 - 1, 28, 12, 30)));
        recentList.add(new Recent_Msg(
                new Peo("邓伦", R.drawable.n2),
                new Msg("test2 msg", Msg.TYPE_RECEIVED),
                new Date(2020 - 1900, 11 - 1, 27, 11, 29)));
        recentList.add(new Recent_Msg(
                new Peo("白敬亭", R.drawable.n14),
                new Msg("test3 msg", Msg.TYPE_SENT),
                new Date(2020 - 1900, 11 - 1, 26, 10, 28)));
    }
}