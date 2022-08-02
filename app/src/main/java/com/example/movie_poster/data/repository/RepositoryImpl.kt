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
            apiService.getAllFilms().FilmsList.map {
                FilmInfo(
                    id = it?.id ?: 0,
                    title =  it?.title ?: "",
                    year = it?.year ?: 0,
                    duration = it?.duration ?: "",
                    genre = it?.genre?.map {
                        GenreInfo(genre = it?.genre ?: "")
                    } ?: emptyList(),
                    image = it?.image ?: "",
                    rating = it?.rating ?: 0.0,
                )
            }
        } catch (e:Exception) {
            Log.d("MyLogException", "Failed to load <getAllFilms> list")
            emptyList()
        }
    }

    override suspend fun getFilmsByTitle(title: String): List<FilmInfo> {
        return try {
            apiService.getFilmsByTitle(title).FilmsList.map {
                FilmInfo(
                    id = it?.id ?: 0,
                    title =  it?.title ?: "",
                    year = it?.year ?: 0,
                    duration = it?.duration ?: "",
                    genre = it?.genre?.map {
                        GenreInfo(genre = it?.genre ?: "")
                    } ?: emptyList(),
                    image = it?.image ?: "",
                    rating = it?.rating ?: 0.0,
                )
            }
        } catch (e: Exception) {
            Log.d("MyLogException", "Failed to load <getFilmsByTitle> list")
            emptyList()
        }
    }

}