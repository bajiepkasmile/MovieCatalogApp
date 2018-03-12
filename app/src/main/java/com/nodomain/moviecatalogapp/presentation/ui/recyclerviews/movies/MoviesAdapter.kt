package com.nodomain.moviecatalogapp.presentation.ui.recyclerviews.movies


import android.view.ViewGroup
import com.nodomain.moviecatalogapp.model.Movie
import com.nodomain.moviecatalogapp.presentation.ui.recyclerviews.base.recyclerviewwithadapterandfooter.RecyclerViewAdapterWithFooter
import com.nodomain.moviecatalogapp.presentation.utils.ReleaseDateFormatConverter


class MoviesAdapter(
        private val releaseDateFormatConverter: ReleaseDateFormatConverter,
        private val listener: MovieItemEventListener,
        private var movies: MutableList<Movie>)
    : RecyclerViewAdapterWithFooter<MovieViewHolder, MoviesFooterViewHolder>() {

    override fun onCreateItemViewHolder(parent: ViewGroup?): MovieViewHolder {
        return MovieViewHolder.create(parent, listener, releaseDateFormatConverter)
    }

    override fun onCreateFooterViewHolder(parent: ViewGroup?) = MoviesFooterViewHolder.create(parent)

    override fun onBindItemViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCountWithoutFooter() = movies.size

    fun setMovies(movies: MutableList<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    fun addMovies(movies: List<Movie>) {
        val positionStart = getItemCountWithoutFooter()
        val insertedItemCount = movies.size
        this.movies.addAll(movies)
        notifyItemRangeInserted(positionStart, insertedItemCount)
    }

    fun updateMovie(movie: Movie) {
        val changedPosition = movies.indexOf(movie)
        movies[changedPosition] = movie
        notifyItemChanged(changedPosition)
    }

    fun showFooter() {
        footerViewHolder?.show()
    }

    fun hideFooter(finalVisibility: Int, onAnimationEndAction: () -> Unit = {}) {
        footerViewHolder?.hide(finalVisibility, onAnimationEndAction)
    }
}