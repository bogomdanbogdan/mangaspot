package com.example.core.utils.utils

import android.content.Context
import com.example.core.R
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun Context.formatRelativeTime(date: Date): String {
    val duration = Duration.between(date.toInstant(), Instant.now())
    val days = duration.toDays()

    return when {
        days == 0L -> getString(R.string.today)
        days == 1L -> getString(R.string.day_ago)
        days in 2..6 -> getString(R.string.days_ago_placeholder, days.toString())
        days >= 7 -> {
            val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault())
            localDate.format(formatter)
        }

        else -> ""
    }
}