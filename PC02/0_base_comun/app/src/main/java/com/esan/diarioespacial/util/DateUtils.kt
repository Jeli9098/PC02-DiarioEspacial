package com.esan.diarioespacial.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/** Fechas en formato AAAA-MM-DD (compatible con minSdk 24, sin java.time). */
object DateUtils {
    private fun formatter() = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun daysAgo(days: Int): String {
        val c = Calendar.getInstance()
        c.add(Calendar.DAY_OF_YEAR, -days)
        return formatter().format(c.time)
    }

    fun today(): String = daysAgo(0)
}
