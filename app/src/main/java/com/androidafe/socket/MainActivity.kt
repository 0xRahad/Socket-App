package com.androidafe.socket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidafe.socket.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var messageList: ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize messageList and userAdapter
        messageList = ArrayList()
        userAdapter = UserAdapter(messageList)

        // Set up RecyclerView
        binding.message.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        SocketHandler.setSocket()

        val mSocket = SocketHandler.getSocket()

        mSocket.connect()

        mSocket.emit("mysqlData")

        mSocket.on("mysqlData"){args->
            if (args[0] != null){
                val jsonArray = args[0] as JSONArray
                runOnUiThread {
                    parseJsonArray(jsonArray)
                }

            }
        }

    }


    private fun parseJsonArray(jsonArray: JSONArray) {
        val gson = Gson()
        messageList.clear()
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val message = gson.fromJson(jsonObject.toString(), Message::class.java)
            messageList.add(0,message)
        }
        userAdapter.notifyDataSetChanged()
        binding.message.scrollToPosition(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        SocketHandler.disconnect()
    }

}
