package com.example.movie_poster.domain

data class FilmInfo(
    val id: Int,
    val title: String,
    val year: Int,
    var description: String = "",
    val duration: String,
    val genre: List<GenreInfo>,
    val image: String,
    val rating: Double,
)