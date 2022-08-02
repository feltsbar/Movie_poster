package com.example.movie_poster.domain

class GetFilmsByTitleUseCase(private val repository: Repository) {
    suspend operator fun invoke(title : String) =repository.getFilmsByTitle(title)
}