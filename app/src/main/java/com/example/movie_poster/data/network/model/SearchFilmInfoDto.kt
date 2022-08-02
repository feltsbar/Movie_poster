package com.example.movie_poster.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchFilmInfoDto(
    @SerializedName("filmId")
    @Expose
    val id : Int?,
    @SerializedName("nameRu")
    @Expose
    val title : String?,
    @SerializedName("posterUrlPreview")
    @Expose
    val image : String?,
    @SerializedName("ratingKinopoisk")
    @Expose
    val rating : Double?,
    @SerializedName("year")
    @Expose
    val year : Int?,
    @SerializedName("filmLength")
    @Expose
    val duration : String?,
    @SerializedName("genres")
    @Expose
    val genre : List<GenreDto?>
)
