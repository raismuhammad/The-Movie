package com.raisproject.themovie.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.raisproject.themovie.adapter.FavoriteAdapter
import com.raisproject.themovie.databinding.ActivityFavoriteBinding
import com.raisproject.themovie.utils.navigateToDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    lateinit var binding : ActivityFavoriteBinding
    lateinit var favAdater : FavoriteAdapter
    val viewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favAdater = FavoriteAdapter {
            navigateToDetail(
                it.id
            )
        }

        prepareRecyclerView()

        viewModel.favoriteMovies.observe(this) {
            Log.d("TAG", "favMovies: $it")
            favAdater.setMovieList(it)
        }
    }

    private fun prepareRecyclerView() {
        binding.rvFavorite.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = favAdater
        }
    }
}