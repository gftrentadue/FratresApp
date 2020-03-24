package com.ppspt.ba.fratresapp.model

data class DonationInterval(
    val intervalStartHour: Int,
    val intervalStartMinute: Int,
    val intervalFinishHour: Int,
    val intervalFinishMinute: Int,
    val bookedUser: ArrayList<User>
)