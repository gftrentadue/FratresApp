package com.ppspt.ba.fratresapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.repositories.DonationDayRepository

class CalendarViewModel internal constructor(
    private val repository: DonationDayRepository
) : ViewModel() {

    fun getDonationDaysList(): LiveData<List<DonationDay>> {
        return repository.getAllDonationDays()
    }
}
