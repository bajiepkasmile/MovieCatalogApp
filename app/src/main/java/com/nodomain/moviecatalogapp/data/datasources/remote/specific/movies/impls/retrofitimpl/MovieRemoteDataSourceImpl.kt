package com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl


import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.MovieRemoteDataSource
import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.mapping.DtoMapper
import com.nodomain.moviecatalogapp.data.datasources.remote.general.API_KEY
import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.retrofit.MoviePageDto
import com.nodomain.moviecatalogapp.data.datasources.remote.general.LANGUAGE_CODE_RU
import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.retrofit.MovieServerApi
import com.nodomain.moviecatalogapp.model.MoviePage
import javax.inject.Inject


class MovieRemoteDataSourceImpl @Inject constructor(
        private val serverApi: MovieServerApi,
        private val dtoMapper: DtoMapper)
    : MovieRemoteDataSource {

    override fun getMoviePage(filterStr: String, pageNumber: Int): MoviePage {
        val moviePageDto = getMoviePageDto(filterStr, pageNumber)
        return if (moviePageDto == null)
            MoviePage()
        else
            dtoMapper.fromDto(moviePageDto)
    }

    private fun getMoviePageDto(filterStr: String, pageNumber: Int): MoviePageDto? {
        return if (filterStr.isEmpty())
            getMoviePageDto(pageNumber)
        else
            getFilteredMoviePageDto(filterStr, pageNumber)
    }

    private fun getMoviePageDto(pageNumber: Int): MoviePageDto? {
        return serverApi
                .getMoviePageDto(API_KEY, LANGUAGE_CODE_RU, pageNumber)
                .execute()
                .body()
    }

    private fun getFilteredMoviePageDto(filterStr: String, pageNumber: Int): MoviePageDto? {
        return serverApi
                .getFilteredMoviePageDto(API_KEY, LANGUAGE_CODE_RU, filterStr, pageNumber)
                .execute()
                .body()
    }
}