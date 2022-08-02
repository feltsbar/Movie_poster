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
                    title = it?.title ?: "",
                    year = it?.year ?: 0,
                    duration = it?.duration ?: "",
                    genre = it?.genre?.map { GenreInfo(genre = it?.genre ?: "") } ?: emptyList(),
                    image = it?.image ?: "",
                    rating = it?.rating ?: 0.0,
                )
            }
        } catch (e: Exception) {
            Log.d("MyLogException", "Failed to load <getAllFilms> list")
            emptyList()
        }
    }

    override suspend fun getFilmsByTitle(title: String): List<FilmInfo> {
        return try {
            apiService.getFilmsByTitle(title).FilmsList.map { it ->
                FilmInfo(
                    id = it?.id ?: 0,
                    title = it?.title ?: "",
                    year = it?.year ?: 0,
                    duration = it?.duration ?: "",
                    genre = it?.genre?.map { GenreInfo(genre = it?.genre ?: "") } ?: emptyList(),
                    image = it?.image ?: "",
                    rating = it?.rating ?: 0.0,
                )
            }
        } catch (e: Exception) {
            Log.d("MyLogException", "Failed to load <getFilmsByTitle> list")
            emptyList()
        }
    }

    override suspend fun getFilmById(filmId: Int): FilmInfo {
        // Метод работает некорректно, сервер, в ответ на запрос, позвращает не то, что нужно
        return try {
            with(apiService.getFilmById(filmId)) {
                FilmInfo(
                    id = this.id ?: 0,
                    title = this.title ?: "",
                    year = this.year ?: 0,
                    duration = this.duration ?: "",
                    genre = this.genre.map { GenreInfo(genre = it?.genre ?: "") },
                    image = this.image ?: "",
                    rating = this.rating ?: 0.0,
                    description = this.description.toString()
                )
            }
        } catch (e: Exception) {
            Log.d("MyLogException", "Failed to load <getFilmById> list")
            FilmInfo(-1, "", -1, "", "", emptyList(), "", -1.0)
        }
    }

}