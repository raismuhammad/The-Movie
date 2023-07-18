package com.raisproject.themovie.usecase

import androidx.lifecycle.LiveData
import com.raisproject.themovie.data.Resource
import com.raisproject.themovie.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getMovies() : Flow<Resource<List<Movie>>>

    fun setLikeMovie(movie: Movie, state: Boolean)

    fun getMovieById(id: Int) : LiveData<Movie>

    fun getLikedMovies() : Flow<List<Movie>>
}