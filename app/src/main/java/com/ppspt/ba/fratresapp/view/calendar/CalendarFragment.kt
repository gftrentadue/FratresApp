package com.ppspt.ba.fratresapp.view.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.databinding.CalendarFragmentBinding
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.utility.InjectorUtils
import com.ppspt.ba.fratresapp.viewmodel.CalendarViewModel
import kotlinx.android.synthetic.main.calendar_fragment.*

class CalendarFragment : Fragment() {
    private val TAG = this::class.java.simpleName

    private val viewModel: CalendarViewModel by viewModels {
        InjectorUtils.provideCalendarViewModelFactory(requireContext())
    }

    private lateinit var binding: CalendarFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CalendarFragmentBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDonationDaysList().observe(viewLifecycleOwner) {
            calendarView.initDonationDays(it as ArrayList<DonationDay>)
        }

        binding.calendarView.setDayClickListener { id ->
            if (id == -1) {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.donation_calendar_no_donation_message),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                findNavController().navigate(
                    CalendarFragmentDirections.actionCalendarToDonationInfo(id)
                )
            }
        }
    }

}
