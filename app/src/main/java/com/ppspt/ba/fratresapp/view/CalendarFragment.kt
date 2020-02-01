package com.ppspt.ba.fratresapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.viewmodel.CalendarViewModel
import com.ppspt.ba.fratresapp.viewmodel.CalendarViewModelFactory
import kotlinx.android.synthetic.main.calendar_fragment.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this.requireActivity(),
            CalendarViewModelFactory(requireActivity().application)
        ).get(CalendarViewModel::class.java)

        viewModel.getDonationDaysList().observe(this) {
            calendarView.initDonationDays(it as ArrayList<DonationDay>)
        }
    }

}
