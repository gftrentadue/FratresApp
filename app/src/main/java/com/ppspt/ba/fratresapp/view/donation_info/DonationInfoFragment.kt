package com.ppspt.ba.fratresapp.view.donation_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.MarkerOptions
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.utility.InjectorUtils
import com.ppspt.ba.fratresapp.utility.Utility
import com.ppspt.ba.fratresapp.viewmodel.DonationInfoViewModel
import kotlinx.android.synthetic.main.donation_info_fragment.*


class DonationInfoFragment : Fragment() {
    private val TAG = this::class.java.simpleName
    private val args: DonationInfoFragmentArgs by navArgs()
    private lateinit var mapView: MapView

    private lateinit var googleMap: GoogleMap

    private var donationInterval: Int? = null
    private var donationStartHour: Int? = null
    private var donationFinishHour: Int? = null

    companion object {
        fun newInstance() =
            DonationInfoFragment()
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
            googleMap = it

            mapView.onResume()
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

        val id = args.donationID
        if (id != -1) {
            viewModel.getDonationFromID(id).observe(viewLifecycleOwner) { dday ->
                // Set values for book donation
                donationInterval = dday.interval
                donationStartHour = dday.stHour
                donationFinishHour = dday.ftHour

                // Set marker on map
                setMarkerOnMap(dday.address)

                // Set other info
                donationInfoAddressValue.text = dday.address

                donationInfoDayValue.text = getString(
                    R.string.donation_info_day_pattern,
                    Utility.zeroFormatter(dday.day),
                    Utility.zeroFormatter(dday.month)
                )
                donationInfoHourValue.text = getString(
                    R.string.donation_info_hour_pattern,
                    Utility.zeroFormatter(dday.stHour),
                    Utility.zeroFormatter(dday.stMinute)
                )
            }

            donationInfoBookButton.setOnClickListener {
                if (donationInterval != null && donationStartHour != null && donationFinishHour != null) {
                    Log.d(TAG, "Click on button to book donation")
                    val dialog = DonationBookDialog()
                    dialog.setCloseDialogListener( object : IDonationBookChooseListener{
                        override fun onDonationIntervalSelected(interval: String) {
                            Log.d(TAG, "Chip selected: $interval")
                        }

                    })

                    dialog.show(parentFragmentManager, "book_intervals_dialog")
                }
            }
        }
    }

    private fun setMarkerOnMap(address: String) {
        val donationLocation =
            Utility.getLocationFromAddress(requireContext(), address)

        if (::googleMap.isInitialized && donationLocation != null) {
            val marker = MarkerOptions().position(donationLocation)
                .title(requireContext().getString(R.string.donation_info_donationday_label))
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(donationLocation, 17f)

            googleMap.addMarker(marker)
            googleMap.moveCamera(cameraUpdate)
        }
    }

}
