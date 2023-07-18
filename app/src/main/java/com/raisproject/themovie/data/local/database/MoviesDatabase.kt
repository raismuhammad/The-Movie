package com.raisproject.themovie.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raisproject.themovie.data.local.dao.MoviesDao
import com.raisproject.themovie.data.local.entity.MoviesEntity

@Database(
    entities = [MoviesEntity::class],
    version = 1
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao() : MoviesDao
}