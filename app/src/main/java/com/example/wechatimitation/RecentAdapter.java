package com.example.wechatimitation;

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

    static class ViewHolder extends RecyclerView.ViewHolder {

        NiceImageView niceImageView;
        TextView recentMsg;
        TextView recentTime;
        TextView recentName;

        public ViewHolder(View view) {
            super(view);
            niceImageView = (NiceImageView) view.findViewById(R.id.recent_contact_image);
            recentMsg = (TextView) view.findViewById(R.id.recent_message);
            recentName = (TextView) view.findViewById(R.id.recent_contact_name);
            recentTime = (TextView) view.findViewById(R.id.recent_time);
        }
    }

    public RecentAdapter(List<Recent_Msg> mList) {
        this.msgList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recent_Msg msg = msgList.get(position);
        holder.niceImageView.setImageResource(msg.getRecent_peo().getImageId());
        holder.recentName.setText(msg.getRecent_peo().getName());
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
