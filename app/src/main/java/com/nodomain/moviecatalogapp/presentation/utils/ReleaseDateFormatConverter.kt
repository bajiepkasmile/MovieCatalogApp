package com.nodomain.moviecatalogapp.presentation.utils


import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


private val LOCALE_RU = Locale("ru")
private val RELEASE_DATE_FORMAT = SimpleDateFormat("d MMMM yyyy", LOCALE_RU)


class ReleaseDateFormatConverter @Inject constructor() {

    fun dateToReleaseDateStr(date: Date): String? = RELEASE_DATE_FORMAT.format(date)
}