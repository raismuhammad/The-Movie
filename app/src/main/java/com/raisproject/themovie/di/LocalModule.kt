package com.raisproject.themovie.di

import android.content.Context
import androidx.room.Room
import com.raisproject.themovie.data.local.dao.MoviesDao
import com.raisproject.themovie.data.local.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app, MoviesDatabase::class.java, "product_database"
        ).allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideMoviesDao(db: MoviesDatabase) : MoviesDao =
        db.getMoviesDao()

}