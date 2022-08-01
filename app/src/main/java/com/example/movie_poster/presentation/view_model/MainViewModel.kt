package com.example.movie_poster.presentation.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.movie_poster.data.repository.RepositoryImpl
import com.example.movie_poster.domain.FilmInfo
import com.example.movie_poster.domain.GetAllFilmsUseCase
import com.example.movie_poster.domain.GetFilmByTitleUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()
    private val getAllFilms = GetAllFilmsUseCase(repository)
    private val getFilmByTitle = GetFilmByTitleUseCase(repository)

    suspend fun loadAllFilms() :List<FilmInfo> {
        Log.d("TEST_OF_LOADING_DATA", getAllFilms.invoke().toString())
        return getAllFilms.invoke()
    }
}