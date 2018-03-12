package com.nodomain.moviecatalogapp.data.datasources.local.specific.movies.impls.roomimpl.room


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.nodomain.moviecatalogapp.data.datasources.local.general.TABLE_NAME_FAVORITE_MOVIES


@Entity(tableName = TABLE_NAME_FAVORITE_MOVIES)
class MovieDbo(
        @ColumnInfo(name = "server_id") @PrimaryKey val serverId: Long,
        @ColumnInfo(name = "poster_url") val posterUrl: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "overview") val overview: String,
        @ColumnInfo(name = "release_date") val releaseDateStr: String)