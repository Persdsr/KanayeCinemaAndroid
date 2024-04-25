package com.kanaye.kanayecinema.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kanaye.kanayecinema.MovieActivity
import com.kanaye.kanayecinema.R
import com.kanaye.kanayecinema.models.Movie
import com.squareup.picasso.Picasso

class MovieScreenshotsAdapter(var screenshotsUrls: List<String>, var context: Context) : RecyclerView.Adapter<MovieScreenshotsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val screenshot: ImageView = view.findViewById(R.id.ivScreenshot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.screenshots_in_movie, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return screenshotsUrls.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var imageUrl = screenshotsUrls[position]
        imageUrl = imageUrl.replace("localhost", "192.168.0.2")
        Picasso.get().load(imageUrl).into(holder.screenshot)
    }
}