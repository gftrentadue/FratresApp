package com.ppspt.ba.fratresapp.repositories

import androidx.lifecycle.LiveData
import com.ppspt.ba.fratresapp.dao.DonationDayDao
import com.ppspt.ba.fratresapp.model.DonationDay

class DonationDayRepository(private val donationDayDao: DonationDayDao) {
    val allDonationDay = donationDayDao.getAll()

    fun getDonationByID(id: Int): LiveData<DonationDay> {
        return donationDayDao.findByID(id)
    }
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