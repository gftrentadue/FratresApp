package com.ppspt.ba.fratresapp.model

import androidx.room.Embedded
import com.ppspt.ba.fratresapp.utility.Utility

class DonationInterval(
    val intervalStartHour: Int,
    val intervalStartMinute: Int,
    val intervalFinishHour: Int,
    val intervalFinishMinute: Int,
    @Embedded val bookedUser: ArrayList<User> = arrayListOf()
) {
    fun getStringInterval() =
        "${Utility.zeroFormatter(intervalStartHour)}:${Utility.zeroFormatter(intervalStartMinute)} - ${Utility.zeroFormatter(
            intervalFinishHour
        )}:${Utility.zeroFormatter(
            intervalFinishMinute
        )}"

}