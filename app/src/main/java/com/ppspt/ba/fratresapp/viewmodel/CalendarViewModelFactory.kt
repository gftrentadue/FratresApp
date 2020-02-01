package com.ppspt.ba.fratresapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CalendarViewModelFactory(application: Application) : ViewModelProvider.Factory {
    private val currentApplication: Application

    init {
        currentApplication = application
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CalendarViewModel(currentApplication) as T
    }
}