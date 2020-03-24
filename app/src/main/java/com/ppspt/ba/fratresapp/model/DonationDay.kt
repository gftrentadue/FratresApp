package com.ppspt.ba.fratresapp.model

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

const val DONATION_TABLE_NAME: String = "DonationTable"
const val DAY_COLUMN_NAME: String = "DAY"
const val MONTH_COLUMN_NAME: String = "MONTH"
const val YEAR_COLUMN_NAME: String = "YEAR"
const val ADDRESS_COLUMN_NAME: String = "ADDRESS"
const val STHOUR_COLUMN_NAME: String = "STHOUR"
const val STMINUTE_COLUMN_NAME: String = "STMINUTE"
const val FTHOUR_COLUMN_NAME: String = "FTHOUR"
const val FTMINUTE_COLUMN_NAME: String = "FTMINUTE"
const val INTERVAL_COLUMN_NAME: String = "INTERVAL"
const val BOOKING_COLUMN_NAME: String = "BOOKING_INTERVAL"

@Entity
data class DonationDay(
    @PrimaryKey val ID: Int,
    @ColumnInfo(name = DAY_COLUMN_NAME) val day: Int,
    @ColumnInfo(name = MONTH_COLUMN_NAME) val month: Int,
    @ColumnInfo(name = YEAR_COLUMN_NAME) val year: Int,
    @ColumnInfo(name = ADDRESS_COLUMN_NAME) val address: String,
    @ColumnInfo(name = STHOUR_COLUMN_NAME) val stHour: Int,
    @ColumnInfo(name = STMINUTE_COLUMN_NAME) val stMinute: Int,
    @ColumnInfo(name = FTHOUR_COLUMN_NAME) val ftHour: Int,
    @ColumnInfo(name = FTMINUTE_COLUMN_NAME) val ftMinute: Int,
    @ColumnInfo(name = INTERVAL_COLUMN_NAME) val interval: Int,
    @ColumnInfo(name = BOOKING_COLUMN_NAME) val bookingInterval: ArrayList<DonationInterval>
) {
    init {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.set(Calendar.HOUR, stHour)
        calendar.set(Calendar.MINUTE, stMinute)
        calendar.set(Calendar.SECOND, 0)
        val startDonation = calendar.timeInMillis
        calendar.set(Calendar.HOUR, ftHour)
        calendar.set(Calendar.MINUTE, ftMinute)
        calendar.set(Calendar.SECOND, 0)
        val endDonation = calendar.timeInMillis
        val durationInMinute = ((endDonation - startDonation) / 1000) / 60

        val donationSessionNum = durationInMinute / interval
        var bookInterval: DonationInterval
        var startHour = stHour
        var startMinute = stMinute
        var finishHour = stHour
        var finishMinute = stMinute

        for (i in 0 until donationSessionNum) {
            if (startMinute + interval == 60) {
                finishHour++
                finishMinute = 0
            } else {
                finishMinute += interval
            }

            bookInterval =
                DonationInterval(startHour, startMinute, finishHour, finishMinute, arrayListOf())
            bookingInterval.add(bookInterval)
            startHour = finishHour
            startMinute = finishMinute
        }
        Log.d("DonationDayInit", "Intervals: ${println(bookingInterval)}")
    }
}