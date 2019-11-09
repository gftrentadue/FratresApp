package com.ppspt.ba.fratresapp.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.applandeo.materialcalendarview.EventDay
import com.ppspt.ba.fratresapp.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val TAG = this::class.java.simpleName

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCalendar()
    }

    private fun initCalendar() {
        // TODO: get donation date from server as arraylist
        val events = arrayListOf<EventDay>()
        for (x in 3 until 30 step 7){
            val date = Calendar.getInstance(TimeZone.getDefault(), Locale.ITALIAN)

            date[Calendar.DAY_OF_MONTH] = x

            events.add(EventDay(date, R.drawable.ic_icon_heart_red, Color.RED))
        }

        calendarView.setEvents(events)
    }

}
