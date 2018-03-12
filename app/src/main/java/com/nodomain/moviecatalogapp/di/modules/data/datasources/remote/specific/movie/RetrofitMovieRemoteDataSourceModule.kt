package com.nodomain.moviecatalogapp.di.modules.data.datasources.remote.specific.movie


import com.nodomain.moviecatalogapp.data.datasources.remote.general.BASE_URL
import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.MovieRemoteDataSource
import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.MovieRemoteDataSourceImpl
import com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.retrofit.MovieServerApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetrofitMovieRemoteDataSourceModule {

    @Provides
    fun provideMovieRemoteDataSource(dataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource =
            dataSourceImpl

    @Singleton
    @Provides
    fun provideMovieServerApi() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieServerApi::class.java)
}