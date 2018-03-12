package com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl.room


import android.arch.persistence.room.*
import com.nodomain.moviecatalogapp.data.datasources.local.general.TABLE_NAME_FAVORITE_MOVIES


@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_NAME_FAVORITE_MOVIES")
    fun getFavoriteMovieDbos(): List<MovieDbo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavoriteMovieDbo(movieDbo: MovieDbo)

    @Delete
    fun removeFavoriteMovieDbo(movieDbo: MovieDbo)
}