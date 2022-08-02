package com.example.movie_poster.data.network

import com.example.movie_poster.data.network.model.DetailFilmInfoDto
import com.example.movie_poster.data.network.model.TotalFindInfoDto
import com.example.movie_poster.data.network.model.TotalInfoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("X-API-KEY: bb0feaad-a444-4833-bed1-10f709602c87", "Content-Type: application/json")
    @GET("/api/v2.2/films/top")
    suspend fun getAllFilms(
        @Query(QUERY_PARAM_TYPE) type: String = QUERY_PARAM_TOP_250_FILMS
    ): TotalInfoDto

    @Headers("X-API-KEY: bb0feaad-a444-4833-bed1-10f709602c87", "Content-Type: application/json")
    @GET("/api/v2.2/films")
    suspend fun getFilmsByTitle(
        @Query(QUERY_PARAM_FILM_KEYWORD) keyword: String
    ): TotalFindInfoDto

    // Метод работает некорректно, сервер, в ответ на запрос, позвращает не то, что нужно
    @Headers("X-API-KEY: bb0feaad-a444-4833-bed1-10f709602c87", "Content-Type: application/json")
    @GET("/api/v2.2/films")
    suspend fun getFilmById(
        @Query(QUERY_PARAM_FILM_ID) id: Int
    ): DetailFilmInfoDto

    companion object {
        private const val QUERY_PARAM_FILM_KEYWORD = "keyword"
        private const val QUERY_PARAM_TYPE = "type"
        private const val QUERY_PARAM_TOP_250_FILMS = "TOP_250_BEST_FILMS"
        private const val QUERY_PARAM_FILM_ID = "id"
    }
}