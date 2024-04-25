package com.kanaye.kanayecinema.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kanaye.kanayecinema.R
import com.kanaye.kanayecinema.models.Comment

class MovieCommentAdapter(var items: List<Comment>, var context: Context) : RecyclerView.Adapter<MovieCommentAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvAuthorNickname: TextView = view.findViewById(R.id.tvAuthorNickname)
        val tvText: TextView = view.findViewById(R.id.tvCommentText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (items[position].author !== null) {
            holder.tvAuthorNickname.text = items[position].author.username
        } else {
            holder.tvAuthorNickname.text = "Анонимус"
        }
        holder.tvText.text = items[position].message

    }
}