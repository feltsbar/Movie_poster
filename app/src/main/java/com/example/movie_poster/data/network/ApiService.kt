package com.example.movie_poster.data.network

import com.example.movie_poster.data.network.model.FilmInfoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("X-API-KEY: bb0feaad-a444-4833-bed1-10f709602c87", "Content-Type: application/json")
    @GET("/api/v2.2/films")
    suspend fun getAllFilms(): List<FilmInfoDto>

    @Headers("X-API-KEY: bb0feaad-a444-4833-bed1-10f709602c87", "Content-Type: application/json")
    @GET("/api/v2.2/films/search-by-keyword{$QUERY_PARAM_FILM_KEYWORD}")
    suspend fun getFilmByTitle(
        @Path(QUERY_PARAM_FILM_KEYWORD) keyword : String,
    ): FilmInfoDto

    companion object {
        private const val QUERY_PARAM_FILM_KEYWORD = "keyword"
    }
}