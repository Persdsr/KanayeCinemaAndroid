package com.kanaye.kanayecinema

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kanaye.kanayecinema.adapters.MovieCommentAdapter
import com.kanaye.kanayecinema.adapters.MovieReviewsAdapter
import com.kanaye.kanayecinema.adapters.MovieScreenshotsAdapter
import com.kanaye.kanayecinema.api.MovieApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val trailer: VideoView = findViewById(R.id.item_detail_trailer)
        val title: TextView = findViewById(R.id.item_detail_title)
        val desc: TextView = findViewById(R.id.item_detail_desc)
        val labelBack: ImageView = findViewById(R.id.ivBack)
        val rvMovieScreenshots: RecyclerView = findViewById(R.id.rvMovieScreenshots)
        val rvMovieReviews: RecyclerView = findViewById(R.id.rvMovieReviews)
        val rvMovieComments: RecyclerView = findViewById(R.id.rvMovieComments)
        val tvAllDesc: TextView = findViewById(R.id.tvAllDesc)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val movieApi = retrofit.create(MovieApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val movie = movieApi.getMovieById(intent.getLongExtra("movieId", 0))
            runOnUiThread {
                val trailerUrl = movie.trailer.replace("localhost", "192.168.0.2")
                // Picasso.get().load(imageUrl).into(image)
                trailer.setVideoPath(trailerUrl)
                trailer.start()
                title.text = movie.title
                desc.text = movie.description.slice(0..100) + "..."

                rvMovieScreenshots.layoutManager = LinearLayoutManager( this@MovieActivity, LinearLayoutManager.HORIZONTAL, false)
                rvMovieScreenshots.adapter = MovieScreenshotsAdapter(movie.screenshots, this@MovieActivity)

                rvMovieReviews.layoutManager = LinearLayoutManager( this@MovieActivity, LinearLayoutManager.HORIZONTAL, false)
                rvMovieReviews.adapter = MovieReviewsAdapter(movie.reviews, this@MovieActivity)

                rvMovieComments.layoutManager = LinearLayoutManager( this@MovieActivity)
                rvMovieComments.adapter = MovieCommentAdapter(movie.comments, this@MovieActivity)

                tvAllDesc.setOnClickListener {
                    desc.text = movie.description
                    tvAllDesc.isVisible = false
                }
            }
        }

        labelBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}