package com.example.wechatimitation

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PeoAdapter(private val peoList: List<Peo>) : RecyclerView.Adapter<PeoAdapter.ViewHolder>() {

    class ViewHolder(var peoView: View) : RecyclerView.ViewHolder(peoView) {
        var peoImage: NiceImageView
        var peoName: TextView

        init {
            peoImage = peoView.findViewById(R.id.contact_image)
            peoName = peoView.findViewById(R.id.contact_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_item, parent, false)
        val holder = ViewHolder(view)
        holder.peoView.setOnClickListener { v -> // 这里的 v 就是设置监听事件的 peoView
            // 获取被点击的联系人名字
//                String s = ((TextView)v.findViewById(R.id.contact_name)).getText().toString();
//                Toast.makeText(v.getContext(), s, Toast.LENGTH_SHORT).show();

            // 第二种方法，可以通过局部变量 holder 来访问
            // holder.peoName.getText().toString();
            val intent = Intent(v.context, Chatting::class.java)

            // 获取被点击的联系人名字和图片
            val names = holder.peoName.text.toString()
            val imageId = holder.peoImage.getTag(R.integer.id_key) as Int
            //                Toast.makeText(v.getContext(), imageId + "", Toast.LENGTH_SHORT).show();
            intent.putExtra("contactsName", names)
            intent.putExtra("contactsImage", imageId)

            // 启动聊天界面
            v.context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val peo = peoList[position]
        holder.peoImage.setImageResource(peo.imageId)
        holder.peoImage.setTag(R.integer.id_key, peo.imageId)
        holder.peoName.text = peo.name
    }

    override fun getItemCount(): Int {
        return peoList.size
    }

}