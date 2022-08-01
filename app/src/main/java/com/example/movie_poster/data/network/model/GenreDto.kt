package com.example.movie_poster.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreDto(
    @SerializedName("genre")
    @Expose
    val genre : String?
)