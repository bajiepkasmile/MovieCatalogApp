package com.nodomain.moviecatalogapp.presentation.ui.recyclerviews.movies


import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nodomain.moviecatalogapp.R
import com.nodomain.moviecatalogapp.model.Movie
import com.nodomain.moviecatalogapp.presentation.extensions.setOnLayoutChangeAction
import com.nodomain.moviecatalogapp.presentation.utils.ReleaseDateFormatConverter
import com.nodomain.moviecatalogapp.presentation.extensions.inflate
import com.nodomain.moviecatalogapp.presentation.extensions.setImageUrl


class MovieViewHolder private constructor(
        itemView: View,
        listener: MovieItemEventListener,
        private val releaseDateFormatConverter: ReleaseDateFormatConverter)
    : RecyclerView.ViewHolder(itemView) {

    companion object {

        fun create(
                parent: ViewGroup?,
                listener: MovieItemEventListener,
                releaseDateFormatConverter: ReleaseDateFormatConverter): MovieViewHolder {
            val itemView = createItemView(parent)
            return MovieViewHolder(itemView, listener, releaseDateFormatConverter)
        }

        private fun createItemView(parent: ViewGroup?) = parent.inflate(R.layout.item_movie)
    }

    private val ivPoster: ImageView = itemView.findViewById(R.id.iv_poster)
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvOverview: TextView = itemView.findViewById(R.id.tv_overview)
    private val tvReleaseDate: TextView = itemView.findViewById(R.id.tv_release_date)
    private val ivFavorite: ImageView = itemView.findViewById(R.id.iv_favorite)

    private lateinit var movie: Movie

    init {
        itemView.setOnClickListener { listener.onMovieClick(movie) }

        ivFavorite.setOnClickListener {
            if (movie.isFavorite)
                listener.onDislikeMovie(movie)
            else
                listener.onLikeMovie(movie)
        }

        with(tvOverview) {
            setOnLayoutChangeAction {
                ellipsize = null
                val fitLineCount = height / lineHeight
                maxLines = fitLineCount
                ellipsize = TextUtils.TruncateAt.END
            }
        }
    }

    fun bind(movie: Movie) {
        this.movie = movie

        val ivFavoriteImageRes =
                if (movie.isFavorite)
                    R.drawable.ic_heart_fill
                else
                    R.drawable.ic_heart

        val tvReleaseDateText =
                if (movie.releaseDate == null)
                    tvReleaseDate.context.getString(R.string.dash)
                else
                    releaseDateFormatConverter.dateToReleaseDateStr(movie.releaseDate)


        ivPoster.scaleType = ImageView.ScaleType.CENTER
        ivPoster.setImageUrl(movie.posterUrl, R.drawable.ic_movie_big, onCompleteAction = {
            ivPoster.scaleType = ImageView.ScaleType.CENTER_CROP
        })

        tvTitle.text = movie.title
        tvOverview.text = movie.overview
        tvReleaseDate.text = tvReleaseDateText
        ivFavorite.setImageResource(ivFavoriteImageRes)
    }
}