package com.example.movie_poster.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TotalInfoDto(
    @SerializedName("pagesCount")
    @Expose
    val pagesCount : Int?,
    @SerializedName("films")
    @Expose
    val FilmsList : List<FilmInfoDto?>
)