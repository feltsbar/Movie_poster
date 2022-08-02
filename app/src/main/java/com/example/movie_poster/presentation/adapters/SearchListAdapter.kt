package com.example.movie_poster.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_poster.R
import com.example.movie_poster.domain.FilmInfo
import com.example.movie_poster.presentation.callbacks.FilmListDiffCallback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.searching_poster_item.view.*

class SearchListAdapter :
    ListAdapter<FilmInfo, SearchListAdapter.FilmsViewHolder>(FilmListDiffCallback) {

    var onPosterClickListener: OnPosterClickListener? = null

    inner class FilmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImage: ImageView = itemView.iv_search_poster_image
        val posterTitle: TextView = itemView.tv_search_poster_title
        val posterRating: RatingBar = itemView.rating_bar
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchListAdapter.FilmsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.searching_poster_item, parent, false)
        return FilmsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchListAdapter.FilmsViewHolder, position: Int) {
        val filmItem = getItem(position)
        with(holder) {
            Picasso.get().load(filmItem.image).into(posterImage)
            posterTitle.text = filmItem.title
            posterRating.rating = (filmItem.rating * 0.5).toFloat()
            itemView.setOnClickListener {
                Log.d("ON_FILM_ITEM_CLICK", "RATING IS ${filmItem.rating.toFloat()}")
                onPosterClickListener?.onPosterClick(filmItem.id)
            }
        }
    }

    interface OnPosterClickListener {
        fun onPosterClick(posterId: Int)
    }
}