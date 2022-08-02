package com.example.movie_poster.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_poster.R
import com.example.movie_poster.databinding.FragmentHomeBinding
import com.example.movie_poster.domain.FilmInfo
import com.example.movie_poster.presentation.adapters.HomeListAdapter
import com.example.movie_poster.presentation.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding == null")
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var viewModel: MainViewModel
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Топ фильмов/сериалов"
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val adapter = HomeListAdapter()
        binding.rvPoster.adapter = adapter
        gridLayoutManager = GridLayoutManager(
            requireContext(),
            3,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvPoster.layoutManager = gridLayoutManager
        scope.launch {
            Log.d("TEXT_OF_LOADING_INFO", viewModel.loadFilmById(1043658).toString())
            val list = viewModel.loadAllFilms()
            requireActivity().runOnUiThread {
                adapter.submitList(list)
            }
        }
//        adapter.onPosterClickListener = object: HomeListAdapter.OnPosterClickListener {
//            override fun onPosterClick(posterItem: FilmInfo) {
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_fragment_container, PosterFragment.newInstance(posterItem.id))
//                    .addToBackStack(null)
//                    .commit()
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        scope.cancel()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}