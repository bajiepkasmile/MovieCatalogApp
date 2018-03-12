package com.nodomain.moviecatalogapp.model


import java.util.*


data class Movie(
        val serverId: Long,
        val posterUrl: String,
        val title: String,
        val overview: String,
        val releaseDate: Date?,
        val isFavorite: Boolean) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (serverId != other.serverId) return false

        return true
    }

    override fun hashCode() = serverId.hashCode()
}