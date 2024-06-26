package com.androidafe.socket

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler  {
    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket(){
        try {
            mSocket = IO.socket("http://192.168.0.127:3000")
        }catch (e:URISyntaxException){

        }
    }

    @Synchronized
    fun getSocket() :Socket{
        return mSocket
    }

    @Synchronized
    fun disconnect() :Socket{
        return mSocket.disconnect()
    }
}