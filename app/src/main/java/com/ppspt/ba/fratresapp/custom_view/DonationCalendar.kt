package com.ppspt.ba.fratresapp.custom_view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.domain.donation_calendar.adapter.DonationCalendarAdapter
import java.util.*
import kotlin.collections.ArrayList

class DonationCalendar(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {
    private lateinit var monthTextView: AppCompatTextView
    private lateinit var previousMonthArrow: AppCompatImageView
    private lateinit var nextMonthArrow: AppCompatImageView
    private lateinit var daysGrid: RecyclerView

    private lateinit var daysAdapter: DonationCalendarAdapter

    private val calendarToShow: Calendar

    private val daysList = arrayListOf<Date>()
    private var donationDaysList = arrayListOf<Date>()

    init {
        calendarToShow = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())

        initLayout(context)

        initCalendar()
    }

    private fun initLayout(context: Context){
        val layout = LayoutInflater.from(context).inflate(R.layout.donation_calendar_layout, this)

        monthTextView = layout.findViewById(R.id.monthTextView)
        previousMonthArrow = layout.findViewById(R.id.previousArrowImage)
        previousMonthArrow.setOnClickListener {
            Log.d("DonationCalendar", "Previous month pressed")
            val currentMonth = calendarToShow.get(Calendar.MONTH)

            if (currentMonth == 0){
                val currentYear = calendarToShow.get(Calendar.YEAR)
                calendarToShow.set(Calendar.MONTH, 11)
                calendarToShow.set(Calendar.YEAR, currentYear - 1)
            } else {
                calendarToShow.set(Calendar.MONTH, currentMonth - 1)
            }

            initCalendar()
        }
        nextMonthArrow = layout.findViewById(R.id.nextArrowImage)
        nextMonthArrow.setOnClickListener {
            Log.d("DonationCalendar", "Next month pressed")
            val currentMonth = calendarToShow.get(Calendar.MONTH)

            if (currentMonth == 11){
                val currentYear = calendarToShow.get(Calendar.YEAR)
                calendarToShow.set(Calendar.MONTH, 0)
                calendarToShow.set(Calendar.YEAR, currentYear + 1)
            } else {
                calendarToShow.set(Calendar.MONTH, currentMonth + 1)
            }

            initCalendar()
        }
        daysGrid = layout.findViewById(R.id.daysGrid)
    }

    private fun initCalendar(){
        calendarToShow.set(Calendar.DAY_OF_MONTH, 1)

        val month = calendarToShow.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        monthTextView.text = month.toUpperCase(Locale.getDefault())

        val previousWeekMondayOffset = 7 - calendarToShow.get(Calendar.DAY_OF_WEEK)
        val lastMonthDay = calendarToShow.getActualMaximum(Calendar.DAY_OF_MONTH)

        calendarToShow.add(Calendar.DAY_OF_MONTH, -previousWeekMondayOffset)

        for (i in 0 until previousWeekMondayOffset + lastMonthDay){
            daysList.add(calendarToShow.time)
            calendarToShow.add(Calendar.DAY_OF_MONTH, 1)
        }
    }

    fun setDonationDays(list: ArrayList<Date>){
        donationDaysList.addAll(list)

        daysAdapter = DonationCalendarAdapter(context, daysList, donationDaysList){
            Log.d("DAY", "Tap on ${daysList[it]}")
        }

        daysGrid.adapter = daysAdapter
    }
}