package com.ppspt.ba.fratresapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.repositories.DonationDayRepository

class DonationInfoViewModel(private val repository: DonationDayRepository) : ViewModel() {

    fun getDonationFromID(id: Int): LiveData<DonationDay> {
        return repository.getDonationByID(id)
    }

    fun addBookingForDonation(donationDay: DonationDay): Int {
        return repository.updateDonation(donationDay)
    }
}
