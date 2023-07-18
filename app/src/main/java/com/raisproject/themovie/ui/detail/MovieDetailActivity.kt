package com.raisproject.themovie.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.raisproject.themovie.R
import com.raisproject.themovie.databinding.ActivityMovieDetailBinding
import com.raisproject.themovie.model.Movie
import com.raisproject.themovie.utils.Constants
import com.raisproject.themovie.utils.convertDate
import com.raisproject.themovie.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailBinding

    private var idMovie = 0

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundleData()
        getMovieData(idMovie)
        backButton()

    }

    private fun getMovieData(idMovie: Int) {
        Log.d("TAG", "getMovieId: $idMovie")
        viewModel.getMovieById(idMovie).observe(this) { data ->
            setDetailMovie(data)
            binding.btnFav.setOnClickListener { setLiked(data) }
        }
    }


    private fun setLiked(data: Movie) {
        var likeState = data.isLike
        likeState = !likeState
        setStatusLiked(likeState)
        viewModel.setLikeMovie(data, likeState)
    }

    private fun setStatusLiked(likedState: Boolean) {
        if (likedState) {
            binding.btnFav.setImageResource(R.drawable.ic_favorite_red)
        } else {
            binding.btnFav.setImageResource(R.drawable.ic_favorite_white)
        }
    }

    private fun setDetailMovie(data: Movie) {
        Log.d("TAG", "setDetailMovie: $data")
        binding.apply {
            ivPoster.loadImage(data.poster_path)
            ivBackdrop.loadImage(data.backdrop_path)
            tvTitle.text = data.title
            tvVoteAverage.text = data.vote_average.toString()
            tvOriginalTitle.text = data.vote_average.toString()

            val releaseDate = data.release_date?.convertDate(
                Constants.YYYY_MM_DD_FORMAT,
                Constants.EEE_D_MMM_YYYY_FORMAT
            )
            tvReleaseDate.text = releaseDate

            tvPopularity.text = data.popularity.toString()
            tvOverview.text = data.overview

            if (!data.isLike) {
                btnFav.setImageResource(R.drawable.ic_favorite_white)
            } else {
                btnFav.setImageResource(R.drawable.ic_favorite_red)
            }

        }
    }

    private fun getBundleData() {
        idMovie = intent.getIntExtra(Constants.EXTRA_ID, 0)
    }

    private fun backButton() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
