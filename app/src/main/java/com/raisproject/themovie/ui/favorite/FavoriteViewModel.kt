package com.raisproject.themovie.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisproject.themovie.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    val favoriteMovies = moviesUseCase.getLikedMovies().asLiveData()

}