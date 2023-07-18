package com.raisproject.themovie.data.local

import androidx.lifecycle.LiveData
import com.raisproject.themovie.data.local.dao.MoviesDao
import com.raisproject.themovie.data.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(val dao: MoviesDao) {

    fun insertMovies(model: List<MoviesEntity>) = dao.insertMovies(model)

    fun getMovies() : Flow<List<MoviesEntity>> = dao.getMovies()

    fun getLikedMovies() : Flow<List<MoviesEntity>> = dao.getLikedMovies()

    fun updateLikeMovie(model: MoviesEntity, state: Boolean) {
        model.isLike = state
        dao.updateLikeMovie(model)
    }

    fun getMovieById(id: Int) : LiveData<MoviesEntity> = dao.getMovieById(id)

}