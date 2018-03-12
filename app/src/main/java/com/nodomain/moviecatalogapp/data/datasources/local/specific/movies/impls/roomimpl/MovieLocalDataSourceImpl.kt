package com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl


import com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.MovieLocalDataSource
import com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl.mapping.DboMapper
import com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl.room.MovieDao
import com.nodomain.moviecatalogapp.model.Movie
import javax.inject.Inject


class MovieLocalDataSourceImpl @Inject constructor(
        private val movieDao: MovieDao,
        private val dboMapper: DboMapper)
    : MovieLocalDataSource {

    override fun getFavoriteMovies(): List<Movie> {
        val movieDbos = movieDao.getFavoriteMovieDbos()
        return dboMapper.fromDbos(movieDbos)
    }

    override fun addFavoriteMovie(movie: Movie) {
        val movieDbo = dboMapper.toDbo(movie)
        movieDao.addFavoriteMovieDbo(movieDbo)
    }

    override fun removeFavoriteMovie(movie: Movie) {
        val movieDbo = dboMapper.toDbo(movie)
        movieDao.removeFavoriteMovieDbo(movieDbo)
    }
}