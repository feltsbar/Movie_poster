package com.example.movie_poster.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_poster.R
import com.example.movie_poster.domain.FilmInfo
import com.example.movie_poster.presentation.callbacks.FilmListDiffCallback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.poster_item.view.*

class HomeListAdapter :
    ListAdapter<FilmInfo, HomeListAdapter.FilmsViewHolder>(FilmListDiffCallback) {

    var onPosterClickListener: OnPosterClickListener? = null

    inner class FilmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImage: ImageView = itemView.iv_poster_image
        val posterTitle: TextView = itemView.tv_poster_title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poster_item, parent, false)
        return FilmsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeListAdapter.FilmsViewHolder, position: Int) {
        val filmItem = getItem(position)
        with(holder) {
            Picasso.get().load(filmItem.image).into(posterImage)
            posterTitle.text = filmItem.title
            itemView.setOnClickListener {
                Log.d("ON_FILM_ITEM_CLICK", "Clicked ${filmItem.title}")
                onPosterClickListener?.onPosterClick(filmItem.image)
            }
        }
    }

    interface OnPosterClickListener {
        fun onPosterClick(imageUrl: String)
    }
}