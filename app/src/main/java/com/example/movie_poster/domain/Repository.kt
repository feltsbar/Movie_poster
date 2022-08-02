package com.example.movie_poster.domain

interface Repository {
    suspend fun getAllFilms() : List<FilmInfo>
    suspend fun getFilmsByTitle(title : String) : List<FilmInfo>
}