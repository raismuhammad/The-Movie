package com.raisproject.themovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raisproject.themovie.databinding.NowplayingItemBinding
import com.raisproject.themovie.model.Movie

class NowPlayingAdapter(var onClick: (Movie) -> Unit) : RecyclerView.Adapter<NowPlayingAdapter.NowPlayingHorizontalViewHolder>() {

    var moviesList = ArrayList<Movie>()

    fun setMovieList(movies: List<Movie>?) {
        this.moviesList = movies as ArrayList<Movie>
        notifyDataSetChanged()
    }

    inner class NowPlayingHorizontalViewHolder(val binding: NowplayingItemBinding) : RecyclerView.ViewHolder(binding.root) {
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
                onClick.invoke(moviesList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowPlayingHorizontalViewHolder {
        return NowPlayingHorizontalViewHolder(NowplayingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NowPlayingHorizontalViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}