package com.nodomain.moviecatalogapp.di.modules.presentation


import com.nodomain.moviecatalogapp.presentation.mvp.moviecatalog.MovieCatalogMvpPresenter
import com.nodomain.moviecatalogapp.presentation.mvp.moviecatalog.MovieCatalogMvpPresenterImpl
import dagger.Module
import dagger.Provides


@Module
class PresenterModule {

    @Provides
    fun provideMovieCatalogMvpPresenter(presenterImpl: MovieCatalogMvpPresenterImpl): MovieCatalogMvpPresenter =
            presenterImpl
}