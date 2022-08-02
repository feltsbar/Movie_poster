package com.example.movie_poster.domain

class GetFilmByIdUseCase(private val repository: Repository) {
    suspend operator fun invoke(filmId : Int) = repository.getFilmById(filmId)
}