package com.androidafe.socket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainViewModel : ViewModel(){
    private val _socketStatus = MutableLiveData(false)
    val socketStatus : LiveData<Boolean> = _socketStatus

    private val _message = MutableLiveData<Pair<Boolean, String>>()
    val message :LiveData<Pair<Boolean,String>> = _message

    fun setStatus(status: Boolean){
        GlobalScope.launch {

        }
    }

    fun setMessage(){

    }
}