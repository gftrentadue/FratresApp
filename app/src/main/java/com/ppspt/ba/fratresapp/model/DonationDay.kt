package com.ppspt.ba.fratresapp.model

import androidx.room.*
import com.ppspt.ba.fratresapp.utility.DonationConverters

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
class DonationDay(
    @PrimaryKey val ID: Int = 0,
    @ColumnInfo(name = DAY_COLUMN_NAME) val day: Int = 1,
    @ColumnInfo(name = MONTH_COLUMN_NAME) val month: Int = 1,
    @ColumnInfo(name = YEAR_COLUMN_NAME) val year: Int = 1,
    @ColumnInfo(name = ADDRESS_COLUMN_NAME) val address: String = "",
    @ColumnInfo(name = STHOUR_COLUMN_NAME) val stHour: Int = 1,
    @ColumnInfo(name = STMINUTE_COLUMN_NAME) val stMinute: Int = 1,
    @ColumnInfo(name = FTHOUR_COLUMN_NAME) val ftHour: Int = 1,
    @ColumnInfo(name = FTMINUTE_COLUMN_NAME) val ftMinute: Int = 1,
    @Embedded @TypeConverters(DonationConverters::class) val intervals: List<DonationInterval> = emptyList()
) {
    constructor() : this(0,1,1,1,"", 1,1,1,1, emptyList())
}