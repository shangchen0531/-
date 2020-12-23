package com.example.wechatimitation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity {

    private List<Peo> peoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        initPeos();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.contact_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 改变RecyclerView 为横向布局
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new PeoAdapter(peoList));
    }

    private void initPeos() {
        peoList.add(new Peo("朱一龙", R.drawable.n1));
        peoList.add(new Peo("邓伦", R.drawable.n2));
        peoList.add(new Peo("王大陆", R.drawable.n3));
        peoList.add(new Peo("范丞丞", R.drawable.n4));
        peoList.add(new Peo("张云雷", R.drawable.n5));
        peoList.add(new Peo("刘翔", R.drawable.n6));
        peoList.add(new Peo("林更新", R.drawable.n7));
        peoList.add(new Peo("吴秀波", R.drawable.n8));
        peoList.add(new Peo("李易峰", R.drawable.n9));
        peoList.add(new Peo("成毅", R.drawable.n10));
        peoList.add(new Peo("黄渤", R.drawable.n11));
        peoList.add(new Peo("权志龙", R.drawable.n12));
        peoList.add(new Peo("李小龙", R.drawable.n13));
        peoList.add(new Peo("白敬亭", R.drawable.n14));
        peoList.add(new Peo("张纪中", R.drawable.n15));
        peoList.add(new Peo("魏大勋", R.drawable.n16));
        peoList.add(new Peo("边伯贤", R.drawable.n17));
        peoList.add(new Peo("胡先煦", R.drawable.n18));
        peoList.add(new Peo("林正英", R.drawable.n19));
        peoList.add(new Peo("韩永华", R.drawable.n20));
        peoList.add(new Peo("张伦硕", R.drawable.n21));
        peoList.add(new Peo("李宁", R.drawable.n22));
        peoList.add(new Peo("经超", R.drawable.n23));
        peoList.add(new Peo("刘宪华", R.drawable.n24));
        peoList.add(new Peo("张嘉译", R.drawable.n25));
        peoList.add(new Peo("沈浩", R.drawable.n26));
        peoList.add(new Peo("许凯", R.drawable.n27));
        peoList.add(new Peo("王千源", R.drawable.n28));
        peoList.add(new Peo("徐小明", R.drawable.n29));
        peoList.add(new Peo("罗晋", R.drawable.n30));
        peoList.add(new Peo("高以翔", R.drawable.n31));
        peoList.add(new Peo("龚俊", R.drawable.n32));
        peoList.add(new Peo("王泷正", R.drawable.n33));
        peoList.add(new Peo("田蕤", R.drawable.n34));
        peoList.add(new Peo("陈晓", R.drawable.n35));
        peoList.add(new Peo("佟大为", R.drawable.n36));
        peoList.add(new Peo("高伟光", R.drawable.n37));
        peoList.add(new Peo("水木年华", R.drawable.n38));
        peoList.add(new Peo("李栋旭", R.drawable.n39));
        peoList.add(new Peo("牛骏峰", R.drawable.n40));
        peoList.add(new Peo("袁冰妍", R.drawable.n41));
        peoList.add(new Peo("小雪", R.drawable.n42));
        peoList.add(new Peo("马苏", R.drawable.n43));
        peoList.add(new Peo("伍宇娟", R.drawable.n44));
        peoList.add(new Peo("文咏珊", R.drawable.n45));
        peoList.add(new Peo("白鹿", R.drawable.n46));
        peoList.add(new Peo("江祖平", R.drawable.n47));
        peoList.add(new Peo("曹曦文", R.drawable.n48));
        peoList.add(new Peo("郭书瑶", R.drawable.n49));
        peoList.add(new Peo("施诗", R.drawable.n50));
        peoList.add(new Peo("左小青", R.drawable.n51));
        peoList.add(new Peo("李小萌", R.drawable.n52));
        peoList.add(new Peo("孟子义", R.drawable.n53));
        peoList.add(new Peo("钟楚曦", R.drawable.n54));
        peoList.add(new Peo("陈昱霖", R.drawable.n55));
        peoList.add(new Peo("蔡思贝", R.drawable.n56));
        peoList.add(new Peo("景甜", R.drawable.n57));
        peoList.add(new Peo("梁丹妮", R.drawable.n58));
        peoList.add(new Peo("李彩桦", R.drawable.n59));
        peoList.add(new Peo("高圆圆", R.drawable.n60));
        peoList.add(new Peo("陈凯师", R.drawable.n61));
        peoList.add(new Peo("文梦洋", R.drawable.n62));
        peoList.add(new Peo("许佳琪", R.drawable.n63));
        peoList.add(new Peo("宋祖儿", R.drawable.n64));
        peoList.add(new Peo("李洁", R.drawable.n65));
        peoList.add(new Peo("姜妍", R.drawable.n66));
        peoList.add(new Peo("王莎莎", R.drawable.n67));
        peoList.add(new Peo("翟煦飞", R.drawable.n68));
        peoList.add(new Peo("王思懿", R.drawable.n69));
        peoList.add(new Peo("李小璐", R.drawable.n70));
        peoList.add(new Peo("郭碧婷", R.drawable.n71));
        peoList.add(new Peo("裴珠泫", R.drawable.n72));
        peoList.add(new Peo("张子枫", R.drawable.n73));
        peoList.add(new Peo("秦雪", R.drawable.n74));
        peoList.add(new Peo("姜素拉", R.drawable.n75));
        peoList.add(new Peo("陈瑾", R.drawable.n76));
        peoList.add(new Peo("张馨予", R.drawable.n77));
        peoList.add(new Peo("张柏芝", R.drawable.n78));
        peoList.add(new Peo("刘楚恬", R.drawable.n79));
        peoList.add(new Peo("孙怡", R.drawable.n80));
    }
}