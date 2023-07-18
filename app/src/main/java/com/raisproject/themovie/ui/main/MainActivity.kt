package com.raisproject.themovie.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.raisproject.themovie.adapter.NowPlayingAdapter
import com.raisproject.themovie.data.Resource
import com.raisproject.themovie.databinding.ActivityMainBinding
import com.raisproject.themovie.utils.navigateToDetail
import com.raisproject.themovie.utils.navigateToFavorite
import com.raisproject.themovie.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    lateinit var nowPlayingAdapter : NowPlayingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFavorite.setOnClickListener {
            navigateToFavorite()
        }

        nowPlayingAdapter = NowPlayingAdapter {
            navigateToDetail(
                it.id
            )
        }


        prepareRecyclerView()

        viewModel.movies.observe(this) {
            when (it) {
                is Resource.Success -> {
                    nowPlayingAdapter.setMovieList(it.data)
                    Log.d("TAG", "ResultMovies: ${it.data}")
                }
                is Resource.Loading -> {}
                is Resource.Error -> {
                    showToast(this, "Error")
                    Log.d("TAG", "errorMessage: ${it.message}")
                }
            }
        }
    }

    private fun prepareRecyclerView() {
        binding.rvNowPlaying.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = nowPlayingAdapter
        }
    }
}