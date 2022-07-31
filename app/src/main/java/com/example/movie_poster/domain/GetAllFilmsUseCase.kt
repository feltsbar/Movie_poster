package com.example.movie_poster.domain

class GetAllFilmsUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getAllFilms()
}

