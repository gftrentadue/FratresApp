package com.ppspt.ba.fratresapp.utility

import java.util.*


object Comparator {
    private val calendar = Calendar.getInstance(TimeZone.getDefault())

    fun dayComparator(date: Date, day: Int, month: Int, year: Int): Int {
        val firstDate = getZeroTimeDate(date)

        calendar.set(year, month - 1, day, 0, 0, 0)

        return when {
            firstDate.after(calendar.time) -> {
                -1
            }
            firstDate.before(calendar.time) -> {
                1
            }
            else -> {
                0
            }
        }
    }

    private fun getZeroTimeDate(date: Date): Date {
        var tempDate = date
        calendar.time = tempDate
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        tempDate = calendar.time
        return tempDate
    }
}