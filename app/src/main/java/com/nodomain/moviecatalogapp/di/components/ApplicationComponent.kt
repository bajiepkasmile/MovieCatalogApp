package com.nodomain.moviecatalogapp.di.components


import com.nodomain.moviecatalogapp.App
import com.nodomain.moviecatalogapp.di.modules.ApplicationModule
import com.nodomain.moviecatalogapp.di.modules.data.datasources.local.general.impls.RoomModule
import com.nodomain.moviecatalogapp.di.modules.data.datasources.local.specific.movie.RoomMovieLocalDataSourceModule
import com.nodomain.moviecatalogapp.di.modules.presentation.PresenterModule
import com.nodomain.moviecatalogapp.di.modules.data.datasources.remote.specific.movie.RetrofitMovieRemoteDataSourceModule
import com.nodomain.moviecatalogapp.di.modules.domain.DomainModule
import com.nodomain.moviecatalogapp.presentation.ui.fragments.MovieCatalogFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            ApplicationModule::class,
            RoomModule::class,
            RoomMovieLocalDataSourceModule::class,
            RetrofitMovieRemoteDataSourceModule::class,
            DomainModule::class,
            PresenterModule::class]
)
interface ApplicationComponent {

    fun inject(app: App)

    fun inject(fragment: MovieCatalogFragment)
}