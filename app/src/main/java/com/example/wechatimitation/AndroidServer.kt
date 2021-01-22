package com.example.wechatimitation

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONObject

class AndroidServer {
    private val IP = "118.89.245.32"
    private val uurl = "http://$IP/AndroidServer/index2.php"
    private val client: OkHttpClient
    private val gson: Gson
    private var response: Response? = null
    private val UTF8_BOM = '\uFEFF'
    fun getContactsByUserName(userName: String?): MutableList<Friend>? {
        val requestBody: RequestBody = FormBody.Builder()
                .add("_userName", userName!!)
                .add("_parameter", PARA_GET_PEO_BY_USERNAME)
                .build()

        // parseJSONWithGSON
        try {
            var result = postToServer(requestBody)
            result = removeUTF8BOM(result)
            return gson.fromJson<MutableList<Friend>>(result, object : TypeToken<List<Friend?>?>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun getMsgByTwoUserName(selfName: String?, friendName: String?): List<Msg> {
        val requestBody: RequestBody = FormBody.Builder()
                .add("_selfName", selfName!!)
                .add("_friendName", friendName!!)
                .add("_parameter", PARA_GET_MES_BY_TWO_USERNAME)
                .build()

        // parseJSONWithGSON
        try {
            var result = postToServer(requestBody)
            result = removeUTF8BOM(result)
            return gson.fromJson<List<Msg>>(result, object : TypeToken<List<Msg?>?>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList<Msg>()
    }

    fun login(userName: String?, userPassword: String?): Int {
        val requestBody: RequestBody = FormBody.Builder()
                .add("_userName", userName!!)
                .add("_userPassword", userPassword!!)
                .add("_parameter", PARA_LOGIN)
                .build()

        // parseJSONWithJSONObject
        try {
            var result = postToServer(requestBody)
            result = removeUTF8BOM(result)
            val jsonObject = JSONObject(result)
            // 0: 用户未注册，-1：用户密码错误，1：用户账户和密码正确
            return jsonObject.getInt("status")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    fun register(userName: String?, userPass: String): Boolean {

        // 需要检测密码不是引号，单双都不行
        if (userPass.contains("'") || userPass.contains("\"")) {
            return false
        }
        val requestBody: RequestBody = FormBody.Builder()
                .add("_userName", userName!!)
                .add("_userPassword", userPass)
                .add("_parameter", PARA_REGISTER)
                .build()
        try {
            var result = postToServer(requestBody)
            result = removeUTF8BOM(result)
            val jsonObject = JSONObject(result)
            // false: 注册失败，true：注册成功
            return jsonObject.getBoolean("status")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun addFriend(userName: String?, friendName: String?): Boolean {
        val requestBody: RequestBody = FormBody.Builder()
                .add("_userName", userName!!)
                .add("_friendName", friendName!!)
                .add("_parameter", PARA_ADD_FRIEND)
                .build()
        try {
            var result = postToServer(requestBody)
            result = removeUTF8BOM(result)
            val jsonObject = JSONObject(result)
            // false: 添加失败，true：添加成功
            return jsonObject.getBoolean("status")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun chat(userName: String?, friendName: String?, content: String?): Boolean {
        val requestBody: RequestBody = FormBody.Builder()
                .add("_userName", userName!!)
                .add("_friendName", friendName!!)
                .add("_content", content!!)
                .add("_parameter", PARA_CHAT)
                .build()
        try {
            var result = postToServer(requestBody)
            result = removeUTF8BOM(result)
            val jsonObject = JSONObject(result)
            // false: 发送失败，true：发送成功
            return jsonObject.getBoolean("status")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun postToServer(body: RequestBody?): String? {
        try {
            val request = Request.Builder()
                    .url(uurl)
                    .post(body!!)
                    .build()
            val tt = Thread(Runnable {
                try {
                    response = client.newCall(request).execute()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })
            tt.start()
            tt.join()
            return response!!.body!!.string()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun removeUTF8BOM(s: String?): String {
        var idx = 0
        while (s!![idx] == UTF8_BOM) {
            ++idx
        }
        return s.substring(idx)
    }

    companion object {
        private const val PARA_GET_PEO_BY_USERNAME = "10086"
        private const val PARA_GET_MES_BY_TWO_USERNAME = "10087"
        private const val PARA_LOGIN = "10088"
        private const val PARA_REGISTER = "10089"
        private const val PARA_ADD_FRIEND = "10090"
        private const val PARA_CHAT = "10091"
    }

    init {
        client = OkHttpClient()
        gson = Gson()
    }
}