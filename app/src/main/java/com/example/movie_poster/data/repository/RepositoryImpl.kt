package com.example.movie_poster.data.repository

import android.util.Log
import com.example.movie_poster.data.network.ApiFactory
import com.example.movie_poster.domain.FilmInfo
import com.example.movie_poster.domain.GenreInfo
import com.example.movie_poster.domain.Repository

class RepositoryImpl : Repository {

    private val apiService = ApiFactory.apiService

    override suspend fun getAllFilms(): List<FilmInfo> {
        return try {
            apiService.getAllFilms().map {
                FilmInfo(
                    id = it.id,
                    title =  it.title,
                    year = it.year,
                    duration = it.duration,
                    genre = it.genre.map {
                        GenreInfo(genre = it.genre)
                    },
                    image = it.image,
                    ratingKinopoisk = it.ratingKinopoisk,
                    type = it.type
                )
            }
        } catch (e:Exception) {
            Log.d("MyLogException", "Failed to load TOPICS list")
            emptyList()
        }
    }

    override suspend fun getFilmByTitle(title: String): FilmInfo {
        TODO("Not yet implemented")
    }

}