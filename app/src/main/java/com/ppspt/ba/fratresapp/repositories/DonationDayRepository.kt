package com.ppspt.ba.fratresapp.repositories

import com.ppspt.ba.fratresapp.dao.DonationDayDao

class DonationDayRepository(private val donationDayDao: DonationDayDao) {
    val allDonationDay = donationDayDao.getAll()

}