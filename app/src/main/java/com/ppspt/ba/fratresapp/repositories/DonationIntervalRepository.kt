package com.ppspt.ba.fratresapp.repositories

import com.ppspt.ba.fratresapp.dao.DonationIntervalDao

class DonationIntervalRepository(dao: DonationIntervalDao) {

    companion object {
        @Volatile
        private var instance: DonationIntervalRepository? = null

        fun getInstance(donationIntervalDao: DonationIntervalDao) = instance ?: synchronized(this) {
            instance ?: DonationIntervalRepository(donationIntervalDao).also { instance = it }
        }
    }
}