package com.ppspt.ba.fratresapp.repositories

import com.ppspt.ba.fratresapp.dao.DonationDayDao

class DonationDayRepository(private val donationDayDao: DonationDayDao) {
    val allDonationDay = donationDayDao.getAll()

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: DonationDayRepository? = null

        fun getInstance(donationDayDao: DonationDayDao) =
            instance ?: synchronized(this) {
                instance ?: DonationDayRepository(donationDayDao).also { instance = it }
            }
    }
}