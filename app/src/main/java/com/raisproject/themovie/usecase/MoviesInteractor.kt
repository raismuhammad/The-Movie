package com.raisproject.themovie.usecase

import androidx.lifecycle.LiveData
import com.raisproject.themovie.data.Resource
import com.raisproject.themovie.data.repository.MoviesRepository
import com.raisproject.themovie.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private val moviesRepo: MoviesRepository) : MoviesUseCase {
    override fun getMovies(): Flow<Resource<List<Movie>>> = moviesRepo.getMovies()
    override fun setLikeMovie(movie: Movie, state: Boolean) = moviesRepo.setLikeMovie(movie, state)
    override fun getMovieById(id: Int): LiveData<Movie> = moviesRepo.getMovieById(id)
    override fun getLikedMovies(): Flow<List<Movie>> = moviesRepo.getLikedMovie()
}