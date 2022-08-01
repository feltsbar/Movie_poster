package com.example.movie_poster.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_poster.databinding.FragmentSearchBinding
import com.example.movie_poster.presentation.adapters.SearchListAdapter
import com.example.movie_poster.presentation.view_model.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding == null")
    private lateinit var viewModel: MainViewModel
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val adapter = SearchListAdapter()
        binding.rvSearchList.adapter = adapter
        binding.btnSearch.setOnClickListener {
            scope.launch {
                val list = viewModel.loadFilmsByTitle(binding.etSearchField.text.toString())
                requireActivity().runOnUiThread {
                    adapter.submitList(list)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        scope.cancel()
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}