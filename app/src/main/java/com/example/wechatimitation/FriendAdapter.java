package com.example.wechatimitation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private List<Friend> FriendList;
    private String my_name;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View FriendView;
        NiceImageView FriendImage;
        TextView FriendName;

        public ViewHolder(View view) {
            super(view);
            FriendView = view;
            FriendImage = view.findViewById(R.id.contact_image);
            FriendName = view.findViewById(R.id.contact_name);
        }
    }

    public FriendAdapter(List<Friend> FriendList1, String myname) {
        this.FriendList = FriendList1;
        this.my_name = myname;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.FriendView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里的 v 就是设置监听事件的 FriendView
                // 获取被点击的联系人名字
//                String s = ((TextView)v.findViewById(R.id.contact_name)).getText().toString();
//                Toast.makeText(v.getContext(), s, Toast.LENGTH_SHORT).show();

                // 第二种方法，可以通过局部变量 holder 来访问
                // holder.FriendName.getText().toString();

                Intent intent = new Intent(v.getContext(), Chatting.class);

                // 获取被点击的联系人名字和图片
                String names = holder.FriendName.getText().toString();
                int imageId = (int) holder.FriendImage.getTag(R.integer.id_key);
//                Toast.makeText(v.getContext(), imageId + "", Toast.LENGTH_SHORT).show();
                intent.putExtra("contactsName", names);
                intent.putExtra("contactsImage", imageId);
                intent.putExtra("myName", my_name);

                // 启动聊天界面
                v.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, int position) {
        Friend friend = FriendList.get(position);
        int img_id = friend.getUser_img();
        if (img_id == -1) {
             img_id = R.drawable.profile;
        }
        holder.FriendImage.setImageResource(img_id);
        holder.FriendImage.setTag(R.integer.id_key, img_id);
        holder.FriendName.setText(friend.getUser_name());
    }

    @Override
    public int getItemCount() {
        return FriendList.size();
    }
}
