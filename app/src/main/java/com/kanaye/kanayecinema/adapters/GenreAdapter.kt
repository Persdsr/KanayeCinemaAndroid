package com.kanaye.kanayecinema.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kanaye.kanayecinema.R
import com.kanaye.kanayecinema.models.Genre

class GenreAdapter(var items: List<Genre>, var context: Context) : RecyclerView.Adapter<GenreAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvCategoryTitle: TextView = view.findViewById(R.id.tvCategoryTitle)
        val rvMovieList: RecyclerView = view.findViewById(R.id.rvMovieList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvCategoryTitle.text = items[position].title
        holder.rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.rvMovieList.adapter = MovieAdapter(items[position].movies, context)

    }
}