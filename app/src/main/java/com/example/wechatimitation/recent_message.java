package com.example.wechatimitation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class recent_message extends AppCompatActivity {

    private List<Recent_Msg> recentList = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_message);

        mContext = this;

        initRects();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recent_message_recyclerList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecentAdapter(recentList));

        BottomNavigationView btw = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        BadgeDrawable badge = btw.getOrCreateBadge(R.id.page_1);
        badge.setVisible(true);
        // badge.setNumber(99);

//        BottomNavigationView btw = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//        btw.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.page_1:
//                        Msg.showText(mContext, "You click the page 1");
//                        break;
//                    case R.id.page_2:
//                        Msg.showText(mContext, "You click the page 2");
//                        break;
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });

//        btw.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case R.id.page_1:
//                        Msg.showText(mContext, "You reclick the page 1");
//                        break;
//                    case R.id.page_2:
//                        Msg.showText(mContext, "You reclick the page 2");
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });

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