package com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl.mapping


import com.nodomain.moviecatalogapp.data.datasources.local.general.DatabaseDateFormatConverter
import com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl.room.MovieDbo
import com.nodomain.moviecatalogapp.model.Movie
import javax.inject.Inject


class DboMapper @Inject constructor(private val dateFormatConverter: DatabaseDateFormatConverter) {

    fun fromDbos(movieDbos: List<MovieDbo>) = movieDbos.map { fromDbo(it) }

    fun fromDbo(movieDbo: MovieDbo): Movie {
        val releaseDate = dateFormatConverter.convertToDate(movieDbo.releaseDateStr)
        return Movie(
                movieDbo.serverId,
                movieDbo.posterUrl,
                movieDbo.title,
                movieDbo.overview,
                releaseDate,
                true)
    }

    fun toDbo(movie: Movie): MovieDbo {
        val releaseDateStr = dateFormatConverter.convertToString(movie.releaseDate)
        return MovieDbo(
                movie.serverId,
                movie.posterUrl,
                movie.posterUrl,
                movie.overview,
                releaseDateStr)
    }
}