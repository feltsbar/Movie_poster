package com.example.movie_poster.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmInfoDto(
    @SerializedName("kinopoiskId")
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
    val ratingKinopoisk : Double?,
    @SerializedName("year")
    @Expose
    val year : Int?,
    @SerializedName("filmLength")
    @Expose
    val duration : Int?,
    @SerializedName("type")
    @Expose
    val type : String?,
    @SerializedName("genres")
    @Expose
    val genre : List<GenreDto?>
)