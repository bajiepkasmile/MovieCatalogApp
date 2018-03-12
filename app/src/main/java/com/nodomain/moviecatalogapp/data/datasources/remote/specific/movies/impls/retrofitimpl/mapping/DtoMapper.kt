package com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.mapping


import com.nodomain.moviecatalogapp.data.datasources.remote.general.ServerDateFormatConverter
import com.nodomain.moviecatalogapp.data.datasources.remote.general.BASE_POSTER_URL
import com.nodomain.moviecatalogapp.data.datasources.remote.general.POSTER_SIZE
import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.retrofit.MovieDto
import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.retrofit.MoviePageDto
import com.nodomain.moviecatalogapp.model.Movie
import com.nodomain.moviecatalogapp.model.MoviePage
import javax.inject.Inject


class DtoMapper @Inject constructor(private val dateFormatConverter: ServerDateFormatConverter) {

    fun fromDto(moviePageDto: MoviePageDto): MoviePage {
        val movies = fromDtos(moviePageDto.movieDtos)
        return MoviePage(movies, "", moviePageDto.page, moviePageDto.totalPages)
    }

    fun fromDtos(movieDtos: List<MovieDto>) = movieDtos.map { fromDto(it) }

    fun fromDto(movieDto: MovieDto): Movie {
        val posterUrl = BASE_POSTER_URL + POSTER_SIZE + movieDto.posterPath
        val releaseDate = dateFormatConverter.convertToDate(movieDto.releaseDateStr)
        return Movie(
                movieDto.serverId,
                posterUrl,
                movieDto.title,
                movieDto.overview,
                releaseDate,
                false)
    }
}