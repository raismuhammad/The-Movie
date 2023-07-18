package com.raisproject.themovie.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raisproject.themovie.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase): ViewModel() {

        val movies = moviesUseCase.getMovies().asLiveData()
}