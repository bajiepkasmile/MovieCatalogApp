package com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.retrofit


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieServerApi {

    @GET("discover/movie")
    fun getMoviePageDto(
            @Query("api_key") apiKey: String,
            @Query("language") languageCode: String,
            @Query("page") pageNumber: Int)
            : Call<MoviePageDto>

    @GET("search/movie")
    fun getFilteredMoviePageDto(
            @Query("api_key") apiKey: String,
            @Query("language") languageCode: String,
            @Query("query") filterStr: String,
            @Query("page") pageNumber: Int)
            : Call<MoviePageDto>
}