package com.ppspt.ba.fratresapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

const val DONATION_TABLE_NAME: String = "donation_table"
const val DAY_COLUMN_NAME: String = "DAY"
const val MONTH_COLUMN_NAME: String = "MONTH"
const val YEAR_COLUMN_NAME: String = "YEAR"
const val ADDRESS_COLUMN_NAME: String = "ADDRESS"
const val STHOUR_COLUMN_NAME: String = "STHOUR"
const val STMINUTE_COLUMN_NAME: String = "STMINUTE"
const val FTHOUR_COLUMN_NAME: String = "FTHOUR"
const val FTMINUTE_COLUMN_NAME: String = "FTMINUTE"
const val INTERVAL_COLUMN_NAME: String = "INTERVAL"

@Entity(tableName = DONATION_TABLE_NAME)
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
    @Embedded @NonNull val intervals: ArrayList<DonationInterval> = arrayListOf()
)