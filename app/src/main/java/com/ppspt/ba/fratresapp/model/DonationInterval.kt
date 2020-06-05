package com.ppspt.ba.fratresapp.model

import androidx.room.TypeConverters
import com.ppspt.ba.fratresapp.utility.UserConverters

class DonationInterval(
    val intervalStartHour: Int = 0,
    val intervalStartMinute: Int = 0,
    val intervalFinishHour: Int = 0,
    val intervalFinishMinute: Int = 0,
    @TypeConverters(UserConverters::class)
    val bookedUser: List<User> = emptyList()
) {
    constructor() : this(0,0,0,0, emptyList())
}