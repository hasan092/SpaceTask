package com.example.spacetask.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun isDateGreater(date: Long): Boolean {
        return getDiffYears(date) > 3
    }

    fun getDiffYears(date: Long): Int {
        val launchDate = getCalendar(date * 1000)
        val currentDate = getCalendar(System.currentTimeMillis())
        val launchYear = launchDate.get(Calendar.YEAR)
        val launchMonth = launchDate.get(Calendar.MONTH)
        val launchDay = launchDate.get(Calendar.DAY_OF_MONTH)
        val currentYear = currentDate.get(Calendar.YEAR)
        val currentMonth = currentDate.get(Calendar.MONTH)
        val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)
        var diff = currentYear - launchYear
        if (diff == 3) {
            if(launchMonth < currentMonth) {
                diff++
            } else if (launchMonth == currentMonth && launchDay < currentDay) {
                diff++
            }
        }
        return diff
    }

    fun getCalendar(date: Long): Calendar {
        val cal = Calendar.getInstance()
        cal.timeInMillis = date
        cal.timeZone = TimeZone.getTimeZone("UTC")
        return cal
    }

    fun getVisualDate(dateLong: Long): String {
        val date = Date(dateLong * 1000)
        val simpleDateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm zz")
        return simpleDateFormat.format(date)
    }
}