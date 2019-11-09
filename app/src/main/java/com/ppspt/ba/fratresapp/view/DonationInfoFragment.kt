package com.ppspt.ba.fratresapp.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ppspt.ba.fratresapp.R

class DonationInfoFragment : Fragment() {

    companion object {
        fun newInstance() = DonationInfoFragment()
    }

    private lateinit var viewModel: DonationInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.donation_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DonationInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
