package com.kanaye.kanayecinema

import android.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kanaye.kanayecinema.adapters.GenreAdapter
import com.kanaye.kanayecinema.api.GenreApi
import com.kanaye.kanayecinema.api.MovieApi
import com.kanaye.kanayecinema.databinding.ActivityMainBinding

import com.kanaye.kanayecinema.models.Genre
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val itemsList: RecyclerView = findViewById(R.id.itemList)
        val items = arrayListOf<Genre>()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val genreApi = retrofit.create(GenreApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val genres = genreApi.getGenres()
            runOnUiThread {
                items.addAll(genres)

                itemsList.layoutManager = LinearLayoutManager(this@MainActivity)
                itemsList.adapter = GenreAdapter(items, this@MainActivity)
            }
        }
    }

}