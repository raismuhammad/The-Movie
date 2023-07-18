package com.raisproject.themovie.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.raisproject.themovie.data.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert
    fun insertMovies(movies: List<MoviesEntity>)

    @Query("SELECT * FROM movies_table")
    fun getMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movies_table WHERE isLike = 1")
    fun getLikedMovies(): Flow<List<MoviesEntity>>

    @Update
    fun updateLikeMovie(movie: MoviesEntity)

    @Query("SELECT * FROM movies_table WHERE id=:id")
    fun getMovieById(id: Int): LiveData<MoviesEntity>

}