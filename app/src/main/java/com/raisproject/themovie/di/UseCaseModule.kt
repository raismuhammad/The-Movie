package com.raisproject.themovie.di

import com.raisproject.themovie.usecase.MoviesInteractor
import com.raisproject.themovie.usecase.MoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideUseCaseModule(impl : MoviesInteractor) : MoviesUseCase
}