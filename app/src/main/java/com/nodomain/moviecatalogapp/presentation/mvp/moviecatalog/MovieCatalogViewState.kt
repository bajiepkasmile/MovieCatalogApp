package com.nodomain.moviecatalogapp.presentation.mvp.moviecatalog


import com.nodomain.moviecatalogapp.model.Movie


sealed class MovieCatalogViewState

class Init : MovieCatalogViewState()

class Data(val movies: MutableList<Movie>, val isFirst: Boolean, val isLast: Boolean) : MovieCatalogViewState()

class Loading(val isFirst: Boolean, val isLast: Boolean) : MovieCatalogViewState()

class Error(val exception: Exception) : MovieCatalogViewState()