package com.example.wechatimitation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MsgAdapter(msgList: List<Msg>, private val received_name: String?, private val received_img: Int, sName: String?, sImg: Int) : RecyclerView.Adapter<MsgAdapter.ViewHolder>() {
    private val msgList: List<Msg>
    private var sent_name : String? = "殇尘"
    private var sent_img = R.drawable.profile

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var leftLayout: LinearLayout
        var rightLayout: LinearLayout
        var leftMsg: TextView
        var rightMsg: TextView
        var leftImage: ImageView
        var rightImage: ImageView

        init {
            leftLayout = view.findViewById<View>(R.id.left_layout) as LinearLayout
            rightLayout = view.findViewById<View>(R.id.right_layout) as LinearLayout
            leftMsg = view.findViewById<View>(R.id.left_msg) as TextView
            rightMsg = view.findViewById<View>(R.id.right_msg) as TextView
            leftImage = view.findViewById<View>(R.id.chatting_image_left) as ImageView
            rightImage = view.findViewById<View>(R.id.chatting_image_right) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.msg_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = msgList[position]
        if (msg.getType() == Msg.Companion.TYPE_RECEIVED) {
            // 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
            holder.leftLayout.visibility = View.VISIBLE
            holder.rightLayout.visibility = View.GONE
            holder.leftImage.setImageResource(received_img)
            holder.leftMsg.text = msg.getContent()
        } else if (msg.getType() == Msg.Companion.TYPE_SENT) {
            // 如果是发送的消息，则显示右边的消息布局，将左边的消息布局隐藏
            holder.rightLayout.visibility = View.VISIBLE
            holder.leftLayout.visibility = View.GONE
            holder.rightImage.setImageResource(sent_img)
            holder.rightMsg.text = msg.getContent()
        }
    }

    override fun getItemCount(): Int {
        return msgList.size
    }

    init {
        sent_name = sName
        sent_img = sImg
        this.msgList = msgList
    }
}