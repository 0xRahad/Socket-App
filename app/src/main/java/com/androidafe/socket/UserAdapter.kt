package com.androidafe.socket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidafe.socket.databinding.ItemUserListBinding

class UserAdapter(var userList:ArrayList<Message>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val model:Message = userList.get(position)
        holder.binding.title.text = model.title
        holder.binding.body.text = model.body
    }

    class UserViewHolder(val binding: ItemUserListBinding): RecyclerView.ViewHolder(binding.root) {

    }

}