package com.example.wechatimitation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeoAdapter extends RecyclerView.Adapter<PeoAdapter.ViewHolder> {

    private List<Peo> peoList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View peoView;
        NiceImageView peoImage;
        TextView peoName;

        public ViewHolder(View view) {
            super(view);
            peoView = view;
            peoImage = view.findViewById(R.id.contact_image);
            peoName = view.findViewById(R.id.contact_name);
        }
    }

    public PeoAdapter(List<Peo> peoList1) {
        peoList = peoList1;
    }

    @NonNull
    @Override
    public PeoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.peoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里的 v 就是设置监听事件的 peoView
                // 获取被点击的联系人名字
//                String s = ((TextView)v.findViewById(R.id.contact_name)).getText().toString();
//                Toast.makeText(v.getContext(), s, Toast.LENGTH_SHORT).show();

                // 第二种方法，可以通过局部变量 holder 来访问
                // holder.peoName.getText().toString();

                Intent intent = new Intent(v.getContext(), Chatting.class);

                // 获取被点击的联系人名字和图片
                String names = holder.peoName.getText().toString();
                int imageId = (int) holder.peoImage.getTag(R.integer.id_key);
//                Toast.makeText(v.getContext(), imageId + "", Toast.LENGTH_SHORT).show();
                intent.putExtra("contactsName", names);
                intent.putExtra("contactsImage", imageId);

                // 启动聊天界面
                v.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeoAdapter.ViewHolder holder, int position) {
        Peo peo = peoList.get(position);
        holder.peoImage.setImageResource(peo.getImageId());
        holder.peoImage.setTag(R.integer.id_key, peo.getImageId());
        holder.peoName.setText(peo.getName());
    }

    @Override
    public int getItemCount() {
        return peoList.size();
    }
}
