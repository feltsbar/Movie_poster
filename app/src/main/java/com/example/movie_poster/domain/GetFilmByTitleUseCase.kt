package com.example.movie_poster.domain

class GetFilmByTitleUseCase(private val repository: Repository) {
    suspend operator fun invoke(title : String) =repository.getFilmByTitle(title)
}