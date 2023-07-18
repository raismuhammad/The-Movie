package com.raisproject.themovie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raisproject.themovie.databinding.FavoriteItemBinding
import com.raisproject.themovie.model.Movie

class FavoriteAdapter(var onClick: (Movie) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    var favoriteList = ArrayList<Movie>()

    fun setMovieList(movies: List<Movie>?) {
        Log.d("TAG", "setMovieList: $movies")
        this.favoriteList.clear()
        this.favoriteList = movies as ArrayList<Movie>
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: Movie) {
            with(itemView) {
                binding.tvTitle.setText(movies.title)

                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500" + movies.poster_path)
                    .into(binding.ivNowplaying)
            }
        }

        init {
            binding.root.setOnClickListener {
                onClick.invoke(favoriteList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }
}