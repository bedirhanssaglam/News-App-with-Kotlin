package com.android.newsapp.presentation.util.helpers

import com.android.newsapp.presentation.util.constants.AppConstants
import java.text.SimpleDateFormat
import java.util.*

object DateParser {
    fun convertDate(date: String): String? {
        val parser = SimpleDateFormat(AppConstants.DATE_FORMAT_AT_RESPONSE, Locale.US)
        try {
            val parse: Date? = parser.parse(date)

            val formatter = SimpleDateFormat(AppConstants.DATE_FORMAT_AT_VIEW, Locale.US)
            return parse?.let { formatter.format(it) }

        } catch (e: java.text.ParseException) {
            throw Exception(e.toString())
        }
    }
}