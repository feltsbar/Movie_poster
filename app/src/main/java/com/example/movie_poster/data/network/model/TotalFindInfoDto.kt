package com.example.movie_poster.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TotalFindInfoDto(
    @SerializedName("total")
    @Expose
    val total : Int?,
    @SerializedName("totalPages")
    @Expose
    val totalPages : Int?,
    @SerializedName("items")
    @Expose
    val FilmsList : List<SearchFilmInfoDto?>
)
