package com.raisproject.themovie.di

import com.raisproject.themovie.data.repository.MoviesRepository
import com.raisproject.themovie.data.local.LocalDataSource
import com.raisproject.themovie.data.remote.source.RemoteDataSource
import com.raisproject.themovie.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutor: AppExecutors) : MoviesRepository {
        return MoviesRepository(remoteDataSource, localDataSource, appExecutor)
    }

    @Provides
    @Singleton
    fun provideAppExecutors() = AppExecutors()
}