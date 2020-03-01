package com.ppspt.ba.fratresapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.repositories.DonationDayRepository

class CalendarViewModel internal constructor(
    repository: DonationDayRepository
) : ViewModel() {

    private val dayList: LiveData<List<DonationDay>> = repository.allDonationDay

    fun getDonationDaysList(): LiveData<List<DonationDay>> {
        return dayList
    }
}
