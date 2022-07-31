package com.example.movie_poster.domain

import java.util.ArrayList

data class FilmInfo(
    val id : Int,
    val title : String,
    val year : Int,
    val duration : Int,
    val genre : List<String>,
    val image : String,
    val ratingKinopoisk : Double,
    val type : String
)