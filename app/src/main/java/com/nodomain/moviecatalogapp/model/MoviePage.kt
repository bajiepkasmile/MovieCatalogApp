package com.nodomain.moviecatalogapp.model


data class MoviePage(
        val movies: List<Movie> = emptyList(),
        val filterStr: String = "",
        val number: Int = 1,
        val total: Int = -1) {

    val isFirst: Boolean
        get() = number == 1

    val isLast: Boolean
        get() =  total == number

    fun next() = copy(movies = emptyList(), number = number + 1)
}