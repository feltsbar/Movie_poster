package com.example.movie_poster.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movie_poster.databinding.FragmentPosterBinding
import com.example.movie_poster.domain.FilmInfo
import com.example.movie_poster.presentation.view_model.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PosterFragment : Fragment() {

    private var _binding: FragmentPosterBinding? = null
    private val binding: FragmentPosterBinding
        get() = _binding ?: throw RuntimeException("FragmentPosterBinding == null")
    private lateinit var viewModel: MainViewModel

    private val scope = CoroutineScope(Dispatchers.IO)
    private var filmId: Int = UNDEFINED_FILM_ID
    private var filmInfo: FilmInfo = FilmInfo(-1, "", -1, "", "", emptyList(), "", -1.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPosterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Подробно о фильме"
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        scope.launch {
            filmInfo = viewModel.loadFilmById(filmId)
        }
        with(binding) {
            Picasso.get().load(filmInfo.image).into(filmImage)
            posterTitle.text = filmInfo.title
            ratingBar.rating = (filmInfo.rating * 0.5).toFloat()
            posterYear.text = "Год релиза: ${filmInfo.year}"
            posterDuration.text = "Продолжительность: ${filmInfo.duration}"
            posterGenre.text = "Жанры: " + filmInfo.genre.toString()
            posterDescription.text = filmInfo.description
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        scope.cancel()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(FILM_ID)) {
            throw RuntimeException("Param film id is absent!")
        }
        filmId = args.getInt(FILM_ID, UNDEFINED_FILM_ID)
    }

    companion object {

        private const val FILM_ID = "film_id"
        private const val UNDEFINED_FILM_ID = -1

        fun newInstance(filmId: Int): PosterFragment {
            return PosterFragment().apply {
                arguments = Bundle().apply {
                    putInt(FILM_ID, filmId)
                }
            }
        }

    }
}