package com.raisproject.themovie.mapper

import com.raisproject.themovie.data.local.entity.MoviesEntity
import com.raisproject.themovie.data.remote.response.MoviesResponse
import com.raisproject.themovie.model.Movie

object MovieMapper {

    fun mapResponseToEntity(input: MoviesResponse): List<MoviesEntity> {
        val arrayListEntity = ArrayList<MoviesEntity>()
        input.results?.map {
            it?.let {
                arrayListEntity.add(
                    MoviesEntity(
                        id = it.id,
                        title = it.title,
                        original_title = it.original_title,
                        poster_path = it.poster_path,
                        backdrop_path = it.backdrop_path,
                        release_date = it.release_date,
                        overview = it.overview,
                        popularity = it.popularity,
                        vote_average = it.vote_average
                    )
                )
            }
        }
        return arrayListEntity
    }

    fun mapEntityToDomain(input: List<MoviesEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                original_title = it.original_title,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                release_date = it.release_date,
                overview = it.overview,
                popularity = it.popularity,
                vote_average = it.vote_average,
                isLike = it.isLike,
            )
        }

    fun mapDetailEntityToDetailDomain(input: MoviesEntity): Movie =
        Movie(
            id = input.id,
            title = input.title,
            original_title = input.original_title,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            release_date = input.release_date,
            overview = input.overview,
            popularity = input.popularity,
            vote_average = input.vote_average,
            isLike = input.isLike
        )

    fun mapDomainToEntity(input: Movie): MoviesEntity =
        MoviesEntity(
            id = input.id,
            title = input.title,
            original_title = input.original_title,
            poster_path = input.poster_path,
            backdrop_path = input.backdrop_path,
            release_date = input.release_date,
            overview = input.overview,
            popularity = input.popularity,
            vote_average = input.vote_average,
            isLike = input.isLike
        )
}