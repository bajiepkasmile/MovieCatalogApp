package com.nodomain.moviecatalogapp.presentation.mvp.moviecatalog


import com.nodomain.moviecatalogapp.model.Movie
import com.nodomain.moviecatalogapp.presentation.mvp.base.MvpView


interface MovieCatalogMvpView : MvpView {

    fun applyState(newState: MovieCatalogViewState)

    fun updateMovie(movie: Movie)

    fun showMessage(text: String)
}