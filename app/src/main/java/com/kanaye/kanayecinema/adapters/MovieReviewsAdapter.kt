package com.kanaye.kanayecinema.adapters

import android.content.Context
import android.content.ContextParams
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kanaye.kanayecinema.MovieActivity
import com.kanaye.kanayecinema.R
import com.kanaye.kanayecinema.models.Movie
import com.kanaye.kanayecinema.models.Review
import com.squareup.picasso.Picasso

class MovieReviewsAdapter(var reviews: List<Review>, var context: Context) : RecyclerView.Adapter<MovieReviewsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvReviewTitle)
        val text: TextView = view.findViewById(R.id.tvReviewText)
        val author: TextView = view.findViewById(R.id.tvAuthorName)
        val reviewLine: View = view.findViewById(R.id.vReviewLine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviews.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = reviews[position].title
        holder.text.text = reviews[position].text
        holder.author.text = reviews[position].author.username

        if (reviews[position].review == "POSITIVE") {
            holder.reviewLine.setBackgroundResource(R.color.brand_green)
        }
        else if (reviews[position].review == "NEGATIVE") {
            holder.reviewLine.setBackgroundResource(R.color.brand_red)
        } else {
            holder.reviewLine.setBackgroundColor(Color.GRAY)
        }

    }
}