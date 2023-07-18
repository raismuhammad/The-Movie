package com.raisproject.themovie.data.remote

import com.raisproject.themovie.BuildConfig
import com.raisproject.themovie.data.remote.response.MoviesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieNowPlaying(
    ): MoviesResponse
}