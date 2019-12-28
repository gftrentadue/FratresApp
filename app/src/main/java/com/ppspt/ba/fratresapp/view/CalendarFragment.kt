package com.ppspt.ba.fratresapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.EventDay
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.viewmodel.CalendarViewModel
import kotlinx.android.synthetic.main.calendar_fragment.*
import java.util.*

class CalendarFragment : Fragment() {
    private val TAG = this::class.java.simpleName

    companion object {
        fun newInstance() = CalendarFragment()
    }

    private lateinit var viewModel: CalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.calendar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CalendarViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCalendar()
    }

    private fun initCalendar() {
        // TODO: get donation date from server as arraylist
        val events = arrayListOf<EventDay>()
        val highlightsDay = arrayListOf<Calendar>()

        for (x in 8 until 31 step 7) {
            val date = Calendar.getInstance(TimeZone.getDefault(), Locale.ITALIAN)

            date[Calendar.DAY_OF_MONTH] = x

            events.add(EventDay(date, R.drawable.ic_icon_heart_red))
            highlightsDay.add(date)
        }

        calendarView.setEvents(events)
        calendarView.setHighlightedDays(highlightsDay)

        calendarView.setOnDayClickListener { eventDay ->
            Log.d(TAG, "${eventDay.calendar.get(Calendar.DAY_OF_MONTH)}")

            if (events.indexOf(eventDay) != -1){
                val date = "${eventDay.calendar.get(Calendar.DAY_OF_MONTH)}/${eventDay.calendar.get(Calendar.MONTH)}/${eventDay.calendar.get(Calendar.YEAR)}"

                findNavController().navigate(CalendarFragmentDirections.actionCalendarToDonationInfo(date))
            } else {
                Log.d(TAG, "Is not a donation day!")
            }
        }
    }

}
