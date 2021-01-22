package com.example.wechatimitation

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class RecentAdapter(private val msgList: List<Recent_Msg>, private val my_name: String?) : RecyclerView.Adapter<RecentAdapter.ViewHolder>() {

    class ViewHolder(var recent_msg_view: View) : RecyclerView.ViewHolder(recent_msg_view) {
        var niceImageView: NiceImageView
        var recentMsg: TextView
        var recentTime: TextView
        var recentName: TextView

        init {
            niceImageView = recent_msg_view.findViewById<View>(R.id.recent_contact_image) as NiceImageView
            recentMsg = recent_msg_view.findViewById<View>(R.id.recent_message) as TextView
            recentName = recent_msg_view.findViewById<View>(R.id.recent_contact_name) as TextView
            recentTime = recent_msg_view.findViewById<View>(R.id.recent_time) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recent_message_item, parent, false)
        val holder = ViewHolder(view)
        holder.recent_msg_view.setOnClickListener { v ->
            val intent = Intent(v.context, Chatting::class.java)
            val contact_name = holder.recentName.text.toString()
            val imageId = holder.niceImageView.getTag(R.integer.id_key) as Int
            intent.putExtra("contactsName", contact_name)
            intent.putExtra("contactsImage", imageId)
            intent.putExtra("myName", my_name)
            v.context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = msgList[position]
        var img_id = msg.recent_peo.user_img
        if (img_id == -1) {
            img_id = R.drawable.profile
        }
        holder.niceImageView.setImageResource(img_id)
        holder.niceImageView.setTag(R.integer.id_key, img_id)
        holder.recentName.text = msg.recent_peo.user_name
        holder.recentMsg.text = msg.recent_msg!!.content
        holder.recentTime.text = dealTime(msg.recent_time)
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    private fun dealTime(date: Date?): String {
        val format = SimpleDateFormat("HH:mm")
        format.timeZone = TimeZone.getTimeZone("Asia/Shanghai")
        return format.format(date)
    }

}