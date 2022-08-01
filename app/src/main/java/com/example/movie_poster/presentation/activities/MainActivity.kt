package com.example.movie_poster.presentation.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movie_poster.R
import com.example.movie_poster.databinding.ActivityMainBinding
import com.example.movie_poster.presentation.fragments.HomeFragment
import com.example.movie_poster.presentation.fragments.ProfileFragment
import com.example.movie_poster.presentation.fragments.SearchFragment
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNavigationBar()
        openFragment(HomeFragment.newInstance())
    }

    private fun setupBottomNavigationBar() {
        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    openFragment(HomeFragment.newInstance())
                    true
                }
                R.id.navigation_search -> {
                    openFragment(SearchFragment.newInstance())
                    true
                }
                R.id.navigation_profile -> {
                    openFragment(ProfileFragment.newInstance())
                    true
                }
                else -> throw RuntimeException("Unknown menu item!")
            }
        }
    }

    private fun openFragment(fragment : Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}