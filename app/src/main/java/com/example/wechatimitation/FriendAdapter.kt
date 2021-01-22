package com.example.wechatimitation

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendAdapter(private val FriendList: List<Friend?>?, private val my_name: String?) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    class ViewHolder(var FriendView: View) : RecyclerView.ViewHolder(FriendView) {
        var FriendImage: NiceImageView
        var FriendName: TextView

        init {
            FriendImage = FriendView.findViewById(R.id.contact_image)
            FriendName = FriendView.findViewById(R.id.contact_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_item, parent, false)
        val holder = ViewHolder(view)
        holder.FriendView.setOnClickListener { v -> // 这里的 v 就是设置监听事件的 FriendView
            // 获取被点击的联系人名字
//                String s = ((TextView)v.findViewById(R.id.contact_name)).getText().toString();
//                Toast.makeText(v.getContext(), s, Toast.LENGTH_SHORT).show();

            // 第二种方法，可以通过局部变量 holder 来访问
            // holder.FriendName.getText().toString();
            val intent = Intent(v.context, Chatting::class.java)

            // 获取被点击的联系人名字和图片
            val names = holder.FriendName.text.toString()
            val imageId = holder.FriendImage.getTag(R.integer.id_key) as Int
            //                Toast.makeText(v.getContext(), imageId + "", Toast.LENGTH_SHORT).show();
            intent.putExtra("contactsName", names)
            intent.putExtra("contactsImage", imageId)
            intent.putExtra("myName", my_name)

            // 启动聊天界面
            v.context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = FriendList!![position]
        var img_id = friend.user_img()
        if (img_id == -1) {
            img_id = R.drawable.profile
        }
        holder.FriendImage.setImageResource(img_id)
        holder.FriendImage.setTag(R.integer.id_key, img_id)
        holder.FriendName.text = friend.user_img()
    }

    override fun getItemCount(): Int {
        return FriendList!!.size
    }

}