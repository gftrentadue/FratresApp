package com.ppspt.ba.fratresapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.utility.InjectorUtils
import com.ppspt.ba.fratresapp.viewmodel.DonationInfoViewModel
import kotlinx.android.synthetic.main.donation_info_fragment.*

class DonationInfoFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    private val args: DonationInfoFragmentArgs by navArgs()

    companion object {
        fun newInstance() = DonationInfoFragment()
    }

    private val viewModel: DonationInfoViewModel by viewModels {
        InjectorUtils.provideDonationInfoViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.donation_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = args.donationID
        if (date != -1) {
            viewModel.getDonationFromID(date).observe(viewLifecycleOwner) { dday ->
                Toast.makeText(
                    requireContext(),
                    "DonationInfo: ${dday.day}/${dday.month}/${dday.year}",
                    Toast.LENGTH_LONG
                ).show()
                dateTextView.text = dday.address
            }
        }
    }

}
