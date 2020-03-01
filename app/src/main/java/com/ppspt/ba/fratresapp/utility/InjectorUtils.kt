package com.ppspt.ba.fratresapp.utility

import android.content.Context
import com.ppspt.ba.fratresapp.db.AppDatabase
import com.ppspt.ba.fratresapp.repositories.DonationDayRepository
import com.ppspt.ba.fratresapp.viewmodel.CalendarViewModelFactory
import com.ppspt.ba.fratresapp.viewmodel.DonationInfoViewModelFactory

object InjectorUtils {
    private fun getDonationDayRepository(context: Context): DonationDayRepository {
        return DonationDayRepository.getInstance(AppDatabase.getInstance(context).donationDayDao())
    }

    fun provideCalendarViewModelFactory(context: Context): CalendarViewModelFactory {
        val repository = getDonationDayRepository(context)
        return CalendarViewModelFactory(repository)
    }

    fun provideDonationInfoViewModelFactory(context: Context): DonationInfoViewModelFactory {
        val repository = getDonationDayRepository(context)
        return DonationInfoViewModelFactory(repository)
    }
}