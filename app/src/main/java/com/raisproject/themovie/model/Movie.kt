package com.raisproject.themovie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
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
