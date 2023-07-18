package com.raisproject.themovie.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raisproject.themovie.R
import com.raisproject.themovie.ui.detail.MovieDetailActivity
import com.raisproject.themovie.ui.favorite.FavoriteActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun String.convertDate(inputFormat: String, outputFormat: String): String {
    val formatter = SimpleDateFormat(inputFormat, Locale.US)
    var formatParser = Date()
    if (this.isNotEmpty()) {
        formatParser = formatter.parse(this) ?: Date()
    }
    val newOutputFormat = SimpleDateFormat(outputFormat, Locale.US)
    return newOutputFormat.format(formatParser)
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(Constants.IMAGE_URL + url)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
        .into(this)
}

fun ImageView.loadBackdrop(url: String?) {
    Glide.with(this.context)
        .load(Constants.BACKDROP_URL + url)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
        .into(this)
}

fun Activity.navigateToFavorite() {
    val intent = Intent(this, FavoriteActivity::class.java)
    startActivity(intent)
}



fun Activity.navigateToDetail(id: Int) {
    val intent = Intent(this, MovieDetailActivity::class.java)
    intent.putExtra(Constants.EXTRA_ID, id)
    startActivity(intent)
}