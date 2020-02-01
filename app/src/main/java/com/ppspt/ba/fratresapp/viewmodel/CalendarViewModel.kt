package com.ppspt.ba.fratresapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ppspt.ba.fratresapp.db.AppDatabase
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.repositories.DonationDayRepository

class CalendarViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DonationDayRepository

    private val dayList: LiveData<List<DonationDay>>

    init {
        val dayDao = AppDatabase.getDatabase(application, viewModelScope).donationDayDao()

        repository = DonationDayRepository(dayDao)

        dayList = repository.allDonationDay
    }

    fun getDonationDaysList(): LiveData<List<DonationDay>> {
        return dayList
    }
}
