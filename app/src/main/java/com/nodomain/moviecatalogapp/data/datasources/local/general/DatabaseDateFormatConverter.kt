package com.nodomain.moviecatalogapp.data.datasources.local.general


import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


private val LOCALE_RU = Locale("ru")
private val DATABASE_DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy", LOCALE_RU)


class DatabaseDateFormatConverter @Inject constructor() {

    fun convertToDate(dateStr: String): Date? {
        return try {
            DATABASE_DATE_FORMAT.parse(dateStr)
        } catch (e: ParseException) {
            null
        }
    }

    fun convertToString(date: Date?): String {
        return if (date != null)
            DATABASE_DATE_FORMAT.format(date)
        else
            ""
    }
}