package com.nodomain.moviecatalogapp.presentation.mvp.moviecatalog


import com.nodomain.moviecatalogapp.model.Movie
import com.nodomain.moviecatalogapp.presentation.mvp.base.MvpPresenter


interface MovieCatalogMvpPresenter : MvpPresenter<MovieCatalogMvpView> {

    fun loadMoreMovies()

    fun refreshMovies()

    fun changeFilter(filterStr: String)

    fun selectMovie(movie: Movie)

    fun addMovieToFavorites(movie: Movie)

    fun removeMovieFromFavorites(movie: Movie)
}