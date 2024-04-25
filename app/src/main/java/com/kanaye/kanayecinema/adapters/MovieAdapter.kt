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

class MovieAdapter(var items: List<Movie>, var context: Context) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val verticalPoster: ImageView = view.findViewById(R.id.item_list_image)
        val title: TextView = view.findViewById(R.id.item_list_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = items[position].title
        var genres: String = ""
        /*for (genre in items[position].genres) {
            genres += " $genre"
        }
        holder.genres.text = genres*/
        var imageUrl = items[position].verticalPoster

        try {
            imageUrl = imageUrl.replace("localhost", "192.168.0.2")
            Picasso.get().load(imageUrl).into(holder.verticalPoster)
        } catch (e: Exception) {

        }

        holder.verticalPoster.setOnClickListener {
            val intent = Intent(context, MovieActivity::class.java)
            intent.putExtra("movieId", items[position].id)
            context.startActivity(intent)
        }

    }
}