package com.raisproject.themovie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.raisproject.themovie.data.Resource
import com.raisproject.themovie.data.local.LocalDataSource
import com.raisproject.themovie.data.remote.ApiResponse
import com.raisproject.themovie.data.remote.response.MoviesResponse
import com.raisproject.themovie.data.remote.source.NetworkBoundResource
import com.raisproject.themovie.data.remote.source.RemoteDataSource
import com.raisproject.themovie.mapper.MovieMapper
import com.raisproject.themovie.model.Movie
import com.raisproject.themovie.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors) {

    fun getMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, MoviesResponse>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map {
                    MovieMapper.mapEntityToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<MoviesResponse>> {
                return remoteDataSource.getMoviesNowPlaying()
            }

            override suspend fun saveCallResult(data: MoviesResponse) {
                val moviesList = MovieMapper.mapResponseToEntity(data)
                localDataSource.insertMovies(moviesList)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }
        }.asFlow()

    fun getLikedMovie() : Flow<List<Movie>> {
        return localDataSource.getLikedMovies().map {
            MovieMapper.mapEntityToDomain(it)
        }
    }

    fun setLikeMovie(movies: Movie, state: Boolean) {
        val moviesEntity = MovieMapper.mapDomainToEntity(movies)
        appExecutors.diskIO().execute {
            localDataSource.updateLikeMovie(moviesEntity, state) }
        }

    fun getMovieById(id: Int): LiveData<Movie> {
        return localDataSource.getMovieById(id).map {
            MovieMapper.mapDetailEntityToDetailDomain(it)
        }
    }
}