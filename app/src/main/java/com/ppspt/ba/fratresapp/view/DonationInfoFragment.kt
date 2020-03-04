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
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.utility.InjectorUtils
import com.ppspt.ba.fratresapp.viewmodel.DonationInfoViewModel
import kotlinx.android.synthetic.main.donation_info_fragment.*


class DonationInfoFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    private val args: DonationInfoFragmentArgs by navArgs()
    private lateinit var mapView: MapView

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
        val view = inflater.inflate(R.layout.donation_info_fragment, container, false)

        // Gets the MapView from the XML layout and creates it
        mapView = view.findViewById(R.id.donationMapView)
        mapView.onCreate(savedInstanceState)

        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync {
            it.uiSettings.isMyLocationButtonEnabled = false
            it.isMyLocationEnabled = true

            // Updates the location and zoom of the MapView
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(LatLng(43.1, -87.9), 10f)
            it.animateCamera(cameraUpdate)
        }

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        try {
            MapsInitializer.initialize(this.activity)
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }

        return view
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
