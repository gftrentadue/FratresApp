package com.ppspt.ba.fratresapp.custom_view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.domain.donation_calendar.adapter.CalendarDayAdapter
import com.ppspt.ba.fratresapp.model.DonationDay
import java.util.*

class DonationCalendar(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {
    private lateinit var monthTextView: AppCompatTextView
    private lateinit var previousMonthArrow: AppCompatImageView
    private lateinit var nextMonthArrow: AppCompatImageView
    private lateinit var daysGrid: RecyclerView

    private lateinit var daysAdapter: CalendarDayAdapter

    private val calendarToShow: Calendar =
        Calendar.getInstance(TimeZone.getDefault(), Locale.ITALIAN)

    private val daysList = arrayListOf<Date>()
    private var donationDaysList = arrayListOf<DonationDay>()

    init {

        initLayout(context)

        initCalendar()
    }

    private fun initLayout(context: Context) {
        val layout = LayoutInflater.from(context).inflate(R.layout.donation_calendar_layout, this)

        monthTextView = layout.findViewById(R.id.monthTextView)
        previousMonthArrow = layout.findViewById(R.id.previousArrowImage)
        previousMonthArrow.setOnClickListener {
            var currentMonth = calendarToShow.get(Calendar.MONTH) - 1
            var currentYear = calendarToShow.get(Calendar.YEAR)

            if (currentMonth == 0) {
                currentMonth = 11
                currentYear--
                calendarToShow.set(Calendar.YEAR, currentYear)
            } else {
                currentMonth--
            }

            calendarToShow.set(Calendar.MONTH, currentMonth)

            initCalendar()
            daysAdapter.setDaysMonth(daysList, currentMonth, currentYear)
        }

        nextMonthArrow = layout.findViewById(R.id.nextArrowImage)
        nextMonthArrow.setOnClickListener {
            var currentMonth = calendarToShow.get(Calendar.MONTH) - 1
            var currentYear = calendarToShow.get(Calendar.YEAR)

            if (currentMonth == 11) {
                currentMonth = 0
                currentYear++
                calendarToShow.set(Calendar.YEAR, currentYear)
            } else {
                currentMonth++
            }

            calendarToShow.set(Calendar.MONTH, currentMonth)

            initCalendar()
            daysAdapter.setDaysMonth(daysList, currentMonth, currentYear)
        }

        daysGrid = layout.findViewById(R.id.daysGrid)
    }

    private fun initCalendar() {
        calendarToShow.set(Calendar.DAY_OF_MONTH, 1)

        val month =
            calendarToShow.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        monthTextView.text = month.toUpperCase(Locale.getDefault())

        val previousWeekMondayOffset: Int = if (calendarToShow.get(Calendar.DAY_OF_WEEK) == 1)
            -6
        else
            Calendar.MONDAY - calendarToShow.get(Calendar.DAY_OF_WEEK)

        val lastMonthDay = calendarToShow.getActualMaximum(Calendar.DAY_OF_MONTH)

        calendarToShow.add(Calendar.DAY_OF_MONTH, previousWeekMondayOffset)

        daysList.clear()

        for (i in 0 until -previousWeekMondayOffset + lastMonthDay) {
            daysList.add(calendarToShow.time)
            calendarToShow.add(Calendar.DAY_OF_MONTH, 1)
        }

        if (calendarToShow.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            val currentDay = calendarToShow.get(Calendar.DAY_OF_WEEK)
            for (i in currentDay..8) {
                daysList.add(calendarToShow.time)
                calendarToShow.add(Calendar.DAY_OF_MONTH, 1)
            }
        } else {
            daysList.add(calendarToShow.time)
        }
    }

    fun initDonationDays(list: ArrayList<DonationDay>) {
        donationDaysList.clear()
        donationDaysList.addAll(list)

        daysAdapter = CalendarDayAdapter(context, daysList, donationDaysList) {
            Log.d("DAY", "Tap on ${daysList[it]}")
        }

        val layoutManager = GridLayoutManager(context, 7, GridLayoutManager.VERTICAL, false)
        daysGrid.layoutManager = layoutManager
        //daysGrid.addItemDecoration(GridItemDecorator(context, GridItemDecorator.ALL))

        daysGrid.adapter = daysAdapter
    }
}