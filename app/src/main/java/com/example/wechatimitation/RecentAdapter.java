package com.example.wechatimitation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder> {

    private List<Recent_Msg> msgList;
    private String my_name;

    static class ViewHolder extends RecyclerView.ViewHolder {

        View recent_msg_view;
        NiceImageView niceImageView;
        TextView recentMsg;
        TextView recentTime;
        TextView recentName;

        public ViewHolder(View view) {
            super(view);
            recent_msg_view = view;
            niceImageView = (NiceImageView) view.findViewById(R.id.recent_contact_image);
            recentMsg = (TextView) view.findViewById(R.id.recent_message);
            recentName = (TextView) view.findViewById(R.id.recent_contact_name);
            recentTime = (TextView) view.findViewById(R.id.recent_time);
        }
    }

    public RecentAdapter(List<Recent_Msg> mList, String myname) {
        this.msgList = mList;
        this.my_name = myname;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_message_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        holder.recent_msg_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Chatting.class);

                String contact_name = holder.recentName.getText().toString();
                int imageId = (int) holder.niceImageView.getTag(R.integer.id_key);

                intent.putExtra("contactsName", contact_name);
                intent.putExtra("contactsImage", imageId);
                intent.putExtra("myName", my_name);

                v.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recent_Msg msg = msgList.get(position);
        int img_id = msg.getRecent_peo().getUser_img();
        if (img_id == -1) {
            img_id = R.drawable.profile;
        }
        holder.niceImageView.setImageResource(img_id);
        holder.niceImageView.setTag(R.integer.id_key, img_id);
        holder.recentName.setText(msg.getRecent_peo().getUser_name());
        holder.recentMsg.setText(msg.getRecent_msg().getContent());
        holder.recentTime.setText(dealTime(msg.getRecent_time()));
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    private String dealTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return format.format(date);
    }
}
