package com.ppspt.ba.fratresapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val INTERVAL_TABLE_NAME = "intervals_table"
const val INTERVAL_START_HOUR = "START_HOUR"
const val INTERVAL_START_MINUTE = "START_MINUTE"
const val INTERVAL_FINISH_HOUR = "FINISH_HOUR"
const val INTERVAL_FINISH_MINUTE = "FINISH_MINUTE"
const val INTERVAL_USERS = "BOOKED_USER"

@Entity(tableName = INTERVAL_TABLE_NAME)
class DonationInterval(
    @PrimaryKey
    val ID: Int = 0,

    @ColumnInfo(name = INTERVAL_START_HOUR)
    val intervalStartHour: Int? = 0,

    @ColumnInfo(name = INTERVAL_START_MINUTE)
    val intervalStartMinute: Int? = 0,

    @ColumnInfo(name = INTERVAL_FINISH_HOUR)
    val intervalFinishHour: Int? = 0,

    @ColumnInfo(name = INTERVAL_FINISH_MINUTE)
    val intervalFinishMinute: Int? = 0,

    @ColumnInfo(name = INTERVAL_USERS)
    val bookedUser: List<User>? = emptyList()
)