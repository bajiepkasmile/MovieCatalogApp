package com.nodomain.moviecatalogapp.presentation.ui.recyclerviews.movies


import com.nodomain.moviecatalogapp.model.Movie


interface MovieItemEventListener {

    fun onMovieClick(movie: Movie)

    fun onLikeMovie(movie: Movie)

    fun onDislikeMovie(movie: Movie)
}