package com.example.movie_poster.presentation.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.movie_poster.domain.FilmInfo

object FilmListDiffCallback: DiffUtil.ItemCallback<FilmInfo>() {
    override fun areItemsTheSame(oldItem: FilmInfo, newItem: FilmInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FilmInfo, newItem: FilmInfo): Boolean {
        return oldItem == newItem
    }
}