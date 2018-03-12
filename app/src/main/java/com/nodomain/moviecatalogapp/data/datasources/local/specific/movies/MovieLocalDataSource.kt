package com.nodomain.moviecatalogapp.data.datasources.local.specific.movies


import com.nodomain.moviecatalogapp.model.Movie


interface MovieLocalDataSource {

    fun getFavoriteMovies(): List<Movie>

    fun addFavoriteMovie(movie: Movie)

    fun removeFavoriteMovie(movie: Movie)
}