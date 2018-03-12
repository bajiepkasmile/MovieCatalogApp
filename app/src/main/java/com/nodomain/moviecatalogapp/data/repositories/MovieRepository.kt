package com.nodomain.moviecatalogapp.data.repositories


import com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.MovieLocalDataSource
import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.MovieRemoteDataSource
import com.nodomain.moviecatalogapp.model.Movie
import com.nodomain.moviecatalogapp.model.MoviePage
import javax.inject.Inject


class MovieRepository @Inject constructor(
        private val remoteDataSource: MovieRemoteDataSource,
        private val localDataSource: MovieLocalDataSource) {

    fun getMoviePage(filterStr: String, pageNumber: Int): MoviePage {
        val moviePage = remoteDataSource.getMoviePage(filterStr, pageNumber)
        val favoriteMovies = localDataSource.getFavoriteMovies()
        val markedPageMovies = markFavoriteMovies(moviePage.movies, favoriteMovies)
        return MoviePage(markedPageMovies, filterStr, moviePage.number, moviePage.total)
    }

    fun addMovieToFavorites(movie: Movie) {
        localDataSource.addFavoriteMovie(movie)
    }

    fun removeMovieFromFavorites(movie: Movie) {
        localDataSource.removeFavoriteMovie(movie)
    }

    private fun markFavoriteMovies(sourceMovies: List<Movie>, favoriteMovies: List<Movie>): List<Movie> {
        val markedMovies = mutableListOf<Movie>()
        for (sourceMovie in sourceMovies) {
            val isFavorite = favoriteMovies.contains(sourceMovie)
            val markedMovie = sourceMovie.copy(isFavorite = isFavorite)
            markedMovies.add(markedMovie)
        }
        return markedMovies
    }
}