package com.nodomain.moviecatalogapp.data.datasources.remote.specific.movies.impls.retrofitimpl.retrofit


import com.google.gson.annotations.SerializedName


class MovieDto(
        @SerializedName("id") val serverId: Long,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("title") val title: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("release_date") val releaseDateStr: String?)