package com.nodomain.moviecatalogapp.data.datasources.remote.general


import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


private val LOCALE_RU = Locale("ru")
private val SERVER_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", LOCALE_RU)


class ServerDateFormatConverter @Inject constructor() {

    fun convertToDate(dateStr: String?): Date? {
        return try {
            SERVER_DATE_FORMAT.parse(dateStr)
        } catch (e: ParseException) {
            null
        }
    }
}