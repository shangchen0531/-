package com.example.wechatimitation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class Chatting : AppCompatActivity() {
    private var msgList: List<Msg> = ArrayList()
    private var list2: List<Msg> = ArrayList()
    private var inputText: EditText? = null
    private var send: Button? = null
    private var msgRecyclerView: RecyclerView? = null
    private var adapter: MsgAdapter? = null
    private var ad: AndroidServer? = null
    private var me: Friend? = null
    private var mContext: Context? = null
    var chatting = true
    private var tt: Thread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
        mContext = this
        val data = intent
        val name = data.getStringExtra("contactsName")
        var imageId = data.getIntExtra("contactsImage", R.drawable.profile)
        if (imageId == -1) {
            imageId = R.drawable.profile
        }
        val myName = data.getStringExtra("myName")
        if (ad == null) {
            ad = AndroidServer()
        }
        val arrow = findViewById<View>(R.id.arrow_btn) as ImageView
        arrow.setOnClickListener { finish() }
        val showName = findViewById<View>(R.id.showName) as TextView
        if (me == null) {
            me = ad!!.getContactsByUserName(myName)?.get(0)
            if (me!!.user_img== -1) {
                me!!.user_img = R.drawable.profile
            }
        }
        showName.text = name
        initMsgs(name) // 初始化聊天消息
        inputText = findViewById<View>(R.id.input_text) as EditText
        send = findViewById<View>(R.id.send) as Button
        msgRecyclerView = findViewById<View>(R.id.msg_recycler_view) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        adapter = MsgAdapter(msgList, name, imageId, me!!.user_name, me!!.user_img)
        msgRecyclerView!!.layoutManager = layoutManager
        msgRecyclerView!!.adapter = adapter
        msgRecyclerView!!.scrollToPosition(msgList.size - 1)
        send!!.setOnClickListener {
            val content = inputText!!.text.toString()
            if (!content.isEmpty()) {
                try {
                    if (!ad!!.chat(myName, name, content)) {
                        Msg.showText(mContext, "发送失败")
                    } else {
                        Log.d("chat: ", "发送成功")
                    }
                } catch (e: Exception) {
                    Msg.showText(mContext, "发送失败")
                    e.printStackTrace()
                }


                // 现在发送消息只是保存到服务器，交由线程来刷新聊天框

//                    Msg msg = new Msg(content, Msg.TYPE_SENT);
//                    msgList.add(msg);
//
//                    // 当有新消息时，刷新RecyclerView中的显示
//                    adapter.notifyItemInserted(msgList.size() - 1);
//
//                    // 将RecyclerView 定位到最后一行
//                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
//
                // 清空输入框中的内容
                inputText!!.setText("")
            }
        }
        tt = Thread(Runnable { // Looper.prepare();
            var a: Long
            var b: Long
            a = System.currentTimeMillis()
            while (chatting) {
                b = System.currentTimeMillis()
                if (b - a >= 2000) { // 每 1秒刷新一次聊天框，全部交由线程来刷新，发送消息后不刷新

                    // Msg.showText(mContext, "test");
                    try {
                        list2 = ad!!.getMsgByTwoUserName(myName, name)
                        if (list2.size > msgList.size) {
                            list2.reversed()
//                            Collections.reverse(list2)

                            msgList = list2
//                            msgList.clear()
//                            msgList.addAll(list2)
                            runOnUiThread { // Stuff that updates the UI

//                                Msg msg = new Msg("test", Msg.TYPE_SENT);
//                                msgList.add(msg);
                                adapter!!.notifyDataSetChanged()
                                msgRecyclerView!!.scrollToPosition(msgList.size - 1)
                            }
                            // adapter.notifyItemInserted(msgList.size() - 1);
                            // msgRecyclerView.scrollToPosition(msgList.size() - 1);
                        }

//                                    if (adapter.getItemCount() != msgList.size()) {
//                                        // 当有新消息时，刷新RecyclerView中的显示
//                                        adapter = new MsgAdapter(msgList)
//                                        adapter.notifyItemInserted(msgList.size() - 1);
//
//                                        // 将RecyclerView 定位到最后一行
//                                        msgRecyclerView.scrollToPosition(msgList.size() - 1);
//                                    }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    a = b
                    Log.d("chat", "chatting")
                }
            }
            Log.d("chat", "finish")
        })
        tt!!.start()
    }

    override fun onDestroy() {
        chatting = false
        try {
            tt!!.join()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        chatting = true
        tt!!.start()
    }

    override fun onStop() {
        super.onStop()
        chatting = false
        try {
            tt!!.join()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initMsgs(name: String?) {
        list2 = ad!!.getMsgByTwoUserName(me!!.user_name, name)
        if (list2 != null) {
            Collections.reverse(list2)
            msgList = list2 as List<Msg>
        }

//        msgList.add(new Msg("你好呀", Msg.TYPE_RECEIVED));
//        msgList.add(new Msg("初次见面，我是rap king", Msg.TYPE_RECEIVED));
//        msgList.add(new Msg("你好", Msg.TYPE_SENT));
    }
}