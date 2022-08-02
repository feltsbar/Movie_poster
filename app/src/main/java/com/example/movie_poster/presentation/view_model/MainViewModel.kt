package com.example.movie_poster.presentation.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.movie_poster.data.repository.RepositoryImpl
import com.example.movie_poster.domain.FilmInfo
import com.example.movie_poster.domain.GetAllFilmsUseCase
import com.example.movie_poster.domain.GetFilmByIdUseCase
import com.example.movie_poster.domain.GetFilmsByTitleUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()
    private val getAllFilms = GetAllFilmsUseCase(repository)
    private val getFilmByTitle = GetFilmsByTitleUseCase(repository)
    private val getFilById = GetFilmByIdUseCase(repository)

    suspend fun loadAllFilms(): List<FilmInfo> {
        Log.d("TEST_OF_LOADING_DATA", getAllFilms.invoke().toString())
        return getAllFilms.invoke()
    }

    suspend fun loadFilmsByTitle(title: String): List<FilmInfo> {
        Log.d("TEST_OF_SEARCH_DATA", getFilmByTitle.invoke(title).toString())
        return getFilmByTitle.invoke(title)
    }

    suspend fun loadFilmById(filmId : Int): FilmInfo {
        Log.d("TEST_OF_SEARCH_DATA", getFilById.invoke(filmId).toString())
        return getFilById.invoke(filmId)
    }
}