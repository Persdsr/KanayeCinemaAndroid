package com.kanaye.kanayecinema.api

import com.kanaye.kanayecinema.models.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie")
    suspend fun getMovies(): List<Movie>

    @GET("movie/{movieId}")
    suspend fun getMovieById(@Path("movieId") id: Long): Movie
}