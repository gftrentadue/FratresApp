package com.ppspt.ba.fratresapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ppspt.ba.fratresapp.repositories.DonationDayRepository

class CalendarViewModelFactory(private val repository: DonationDayRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CalendarViewModel(repository) as T
    }
}