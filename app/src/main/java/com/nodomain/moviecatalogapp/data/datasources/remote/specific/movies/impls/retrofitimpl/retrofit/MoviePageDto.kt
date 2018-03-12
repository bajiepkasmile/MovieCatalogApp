package com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.retrofit


import com.google.gson.annotations.SerializedName


class MoviePageDto(
        @SerializedName("page") val page: Int,
        @SerializedName("total_pages") val totalPages: Int,
        @SerializedName("results") val movieDtos: List<MovieDto>)