package com.example.movie_poster.data.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.example.movie_poster.data.network.ApiFactory
import com.example.movie_poster.domain.FilmInfo
import com.example.movie_poster.domain.GenreInfo
import com.example.movie_poster.domain.Repository

class RepositoryImpl : Repository {

    private val apiService = ApiFactory.apiService

    override suspend fun getAllFilms(): List<FilmInfo> {
        return try {
            apiService.getAllFilms().FilmsList.map {
                FilmInfo(
                    id = it?.id ?: 0,
                    title =  it?.title ?: "",
                    year = it?.year ?: 0,
                    duration = it?.duration ?: 0,
                    genre = it?.genre?.map {
                        GenreInfo(genre = it?.genre ?: "")
                    } ?: emptyList(),
                    image = it?.image ?: "",
                    ratingKinopoisk = it?.ratingKinopoisk ?: 0.0,
                    type = it?.type ?: ""
                )
            }
        } catch (e:Exception) {
            Log.d("MyLogException", "Failed to load Films list")
            emptyList()
        }
    }

    override suspend fun getFilmByTitle(title: String): FilmInfo {
        TODO("Not yet implemented")
//        FilmInfo(
//            id = it.id,
//            title =  it.title,
//            year = it.year,
//            duration = it.duration,
//            genre = it.genre.map {
//                GenreInfo(genre = it.genre)
//            },
//            image = it.image,
//            ratingKinopoisk = it.ratingKinopoisk,
//            type = it.type
//        )
    }

}