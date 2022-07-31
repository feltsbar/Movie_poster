package com.example.movie_poster.domain

interface Repository {
    suspend fun getAllFilms() : List<FilmInfo>
    suspend fun getFilmByTitle(title : String) : FilmInfo
}