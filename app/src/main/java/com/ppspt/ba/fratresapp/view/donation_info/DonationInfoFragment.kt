package com.ppspt.ba.fratresapp.view.donation_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
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

    private var selectedInterval = ""

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
                    val dialog = DonationBookDialog(selectedInterval)
                    dialog.setCloseDialogListener(object : IDonationBookChooseListener {
                        override fun onDonationIntervalSelected(interval: String) {
                            // Update selected interval
                            selectedInterval = interval

                            // Update visibility and constraints based on dialog result
                            if (interval.isNotEmpty()) {
                                donationInfoSelectedInterval.text = requireContext().getString(
                                    R.string.donation_info_selected_interval_label,
                                    interval
                                )

                                // Update main visibility
                                donationInfoSelectedInterval.visibility = View.VISIBLE
                                // Update main constraints
                                val constraint = ConstraintSet()
                                constraint.clone(donationInfoMainLayout)
                                constraint.connect(
                                    R.id.donationInfoButtonsLayout,
                                    ConstraintSet.TOP,
                                    R.id.donationInfoSelectedInterval,
                                    ConstraintSet.BOTTOM,
                                    resources.getDimension(R.dimen.margin_40).toInt()
                                )
                                constraint.applyTo(donationInfoMainLayout)

                                // Update button visibility
                                donationInfoConfirmButton.visibility = View.VISIBLE
                                //Update buttons layout constraint
                                val buttonsConstraint = ConstraintSet()
                                buttonsConstraint.clone(donationInfoButtonsLayout)
                                buttonsConstraint.connect(
                                    R.id.donationInfoBookButton,
                                    ConstraintSet.START,
                                    ConstraintSet.PARENT_ID,
                                    ConstraintSet.START,
                                    0
                                )
                                buttonsConstraint.connect(
                                    R.id.donationInfoConfirmButton,
                                    ConstraintSet.START,
                                    R.id.donationInfoBookButton,
                                    ConstraintSet.END,
                                    resources.getDimension(R.dimen.margin_8).toInt()
                                )
                                buttonsConstraint.connect(
                                    R.id.donationInfoConfirmButton,
                                    ConstraintSet.END,
                                    ConstraintSet.PARENT_ID,
                                    ConstraintSet.END,
                                    0
                                )
                                buttonsConstraint.setHorizontalBias(R.id.donationInfoBookButton, 0f)
                                buttonsConstraint.setHorizontalBias(
                                    R.id.donationInfoConfirmButton,
                                    1f
                                )
                                buttonsConstraint.applyTo(donationInfoButtonsLayout)
                            } else {
                                // Update main visibility
                                donationInfoSelectedInterval.visibility = View.GONE
                                // Update main constraints
                                val constraint = ConstraintSet()
                                constraint.clone(donationInfoMainLayout)
                                constraint.connect(
                                    R.id.donationInfoButtonsLayout,
                                    ConstraintSet.TOP,
                                    R.id.donationInfoDayHourLayout,
                                    ConstraintSet.BOTTOM,
                                    resources.getDimension(R.dimen.margin_64).toInt()
                                )
                                constraint.applyTo(donationInfoMainLayout)

                                // Update button visibility
                                donationInfoConfirmButton.visibility = View.GONE
                                //Update buttons layout constraint
                                val buttonsConstraint = ConstraintSet()
                                buttonsConstraint.clone(donationInfoButtonsLayout)
                                buttonsConstraint.connect(
                                    R.id.donationInfoBookButton,
                                    ConstraintSet.START,
                                    ConstraintSet.PARENT_ID,
                                    ConstraintSet.START,
                                    0
                                )
                                buttonsConstraint.connect(
                                    R.id.donationInfoBookButton,
                                    ConstraintSet.END,
                                    ConstraintSet.PARENT_ID,
                                    ConstraintSet.END,
                                    0
                                )
                                buttonsConstraint.setHorizontalBias(
                                    R.id.donationInfoBookButton,
                                    0.5f
                                )
                                buttonsConstraint.applyTo(donationInfoButtonsLayout)
                            }
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
