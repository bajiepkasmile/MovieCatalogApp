package com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies


import com.nodomain.moviecatalogapp.model.MoviePage


interface MovieRemoteDataSource {

    fun getMoviePage(filterStr: String, pageNumber: Int): MoviePage
}