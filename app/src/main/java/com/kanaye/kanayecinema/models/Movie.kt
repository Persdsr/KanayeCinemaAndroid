package com.kanaye.kanayecinema.models

import java.util.Date

data class Movie(
    val id: Long,
    val title: String,
    val duration: Float,
    val fees: Float,
    val slogan: String,
    val description: String,
    val movieFileUrl: String,
    val slugUrl: String,
    val poster: String,
    val verticalPoster: String,
    val screenshots: List<String>,
    val fullPoster: String,
    val reviews: List<Review>,
    val comments: List<Comment>,
    val trailer: String,
    val yearProduction: Int,
    val country: String,
    val budget: Float,
    val ageLimit: Int,
    val author: String,
    val datePremiere: Date,
)

