package com.raisproject.themovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raisproject.themovie.model.Movie
import com.raisproject.themovie.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase): ViewModel() {

    fun setLikeMovie(movie: Movie, state: Boolean) = moviesUseCase.setLikeMovie(movie, state)
    fun getMovieById(id: Int) : LiveData<Movie>  {
         return moviesUseCase.getMovieById(id)
    }

}