package com.raisproject.themovie.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import javax.annotation.Nonnull

@Parcelize
@Entity(tableName = "movies_table")
data class MoviesEntity(
    @PrimaryKey
    @Nonnull
    var id: Int,
    var title: String? = null,
    var original_title: String? = null,
    var poster_path: String? = null,
    var backdrop_path: String? = null,
    var release_date: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var vote_average: Double? = null,
    var isLike: Boolean = false
): Parcelable
