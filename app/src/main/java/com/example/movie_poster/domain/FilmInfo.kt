package com.example.movie_poster.domain

data class FilmInfo(
    val id : Int,
    val title : String,
    val year : Int,
    val duration : Int,
    val genre : List<GenreInfo>,
    val image : String,
    val ratingKinopoisk : Double,
    val type : String
)