package com.nodomain.moviecatalogapp.di.modules.data.datasources.local.specific.movie


import com.nodomain.moviecatalogapp.data.datasources.local.general.impls.room.Database
import com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.MovieLocalDataSource
import com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides


@Module
class RoomMovieLocalDataSourceModule {

    @Provides
    fun provideMovieLocalDataSource(dataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource =
            dataSourceImpl

    @Provides
    fun provideMovieDao(database: Database) = database.movieDao()
}