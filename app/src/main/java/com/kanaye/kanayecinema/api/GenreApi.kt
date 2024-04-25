package com.kanaye.kanayecinema.api

import com.kanaye.kanayecinema.models.Genre
import com.kanaye.kanayecinema.models.Movie
import retrofit2.http.GET

interface GenreApi {
    @GET("genre")
    suspend fun getGenres(): List<Genre>

}