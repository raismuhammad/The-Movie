package com.raisproject.themovie.data.remote.source

import com.raisproject.themovie.data.remote.ApiResponse
import com.raisproject.themovie.data.remote.ApiService
import com.raisproject.themovie.data.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMoviesNowPlaying() : Flow<ApiResponse<MoviesResponse>> {
        return flow {
            try {
                val response = apiService.getMovieNowPlaying()
                val movies = response.results
                movies?.let {
                    if (it.isNotEmpty()) {
                        emit(ApiResponse.Success(response))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}