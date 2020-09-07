package com.ppspt.ba.fratresapp.view.donation_info

import android.app.Dialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.model.DonationInterval
import com.ppspt.ba.fratresapp.model.User
import com.ppspt.ba.fratresapp.utility.Constants
import com.ppspt.ba.fratresapp.utility.InjectorUtils
import com.ppspt.ba.fratresapp.utility.Utility
import com.ppspt.ba.fratresapp.viewmodel.DonationInfoViewModel
import kotlinx.android.synthetic.main.donation_book_dialog.*
import java.util.*
import kotlin.collections.ArrayList


class DonationBookDialog(private val donationDayId: Int, private val selectedInterval: String) : DialogFragment() {
    private lateinit var chipSelectedListener: IDonationBookChooseListener
    private var currentSelectedChip = ""
    private var selectedDonationDay: DonationDay? = null

    private val viewModel: DonationInfoViewModel by viewModels {
        InjectorUtils.provideDonationInfoViewModelFactory(requireContext())
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.donation_book_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Set ColorStateList for change text color on button state change
        val buttonStates = arrayOf(
            intArrayOf(-android.R.attr.state_enabled), intArrayOf(android.R.attr.state_enabled)
        )

        val buttonTextColors = intArrayOf(
            ContextCompat.getColor(requireContext(), R.color.colorWhite),
            ContextCompat.getColor(requireContext(), R.color.colorBlack)
        )
        donationSetBookButton.setTextColor(ColorStateList(buttonStates, buttonTextColors))

        donationSetBookButton.isEnabled = false
        donationSetBookButton.setOnClickListener {
            chipSelectedListener.onDonationIntervalSelected(currentSelectedChip)
            dismiss()
        }

        donationBookCloseIcon.setOnClickListener {
            dismiss()
        }

        viewModel.getDonationFromID(donationDayId).observe(viewLifecycleOwner, Observer { dDay ->
            dDay?.let {
                selectedDonationDay = it

                val intervals = it.intervals
                if (!intervals.isNullOrEmpty()) {
                    setBookIntervals(intervals)
                }
            }
        })
    }

    private fun setBookIntervals(intervals: ArrayList<DonationInterval>) {
        // Set ColorStateList for change background color on chip state change
        val chipStates = arrayOf(
            intArrayOf(-android.R.attr.state_selected), intArrayOf(android.R.attr.state_selected),
            intArrayOf(-android.R.attr.state_activated), intArrayOf(android.R.attr.state_activated),
            intArrayOf(-android.R.attr.state_pressed), intArrayOf(android.R.attr.state_pressed)
        )

        val backgroundColors = intArrayOf(
            ContextCompat.getColor(requireContext(), R.color.colorAccent),
            ContextCompat.getColor(requireContext(), R.color.colorWhite),
            ContextCompat.getColor(requireContext(), R.color.colorAccent),
            ContextCompat.getColor(requireContext(), R.color.colorWhite),
            ContextCompat.getColor(requireContext(), R.color.colorAccent),
            ContextCompat.getColor(requireContext(), R.color.colorWhite)
        )

        // Set ColorStateList for change text color on chip state change
        val textColors = intArrayOf(
            ContextCompat.getColor(requireContext(), R.color.colorWhite),
            ContextCompat.getColor(requireContext(), R.color.colorBlack),
            ContextCompat.getColor(requireContext(), R.color.colorWhite),
            ContextCompat.getColor(requireContext(), R.color.colorBlack),
            ContextCompat.getColor(requireContext(), R.color.colorWhite),
            ContextCompat.getColor(requireContext(), R.color.colorBlack)
        )

        val backgroundStateList = ColorStateList(chipStates, backgroundColors)
        val textStateList = ColorStateList(chipStates, textColors)

        var chip: Chip

        for (interval in intervals) {
            chip = Chip(requireContext())
            chip.text = Utility.getPrintableInterval(requireContext(), interval)

            // Make the chip clickable
            chip.isClickable = true
            chip.isCheckable = true

            // Set chip style
            chip.chipBackgroundColor = backgroundStateList
            chip.setTextColor(textStateList)
            chip.chipStrokeColor = ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorAccentDark
                )
            )
            chip.chipStrokeWidth = resources.getDimension(R.dimen.book_chip_stroke_width)

            // Show the chip icon in chip
            chip.isCloseIconVisible = false
            chip.checkedIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_check_black_24dp)

            donationBookIntervals.addView(chip)

            chip.setOnCheckedChangeListener { chipView, isChecked ->
                val selectedChipText = (chipView as Chip).text.toString()
                if (isChecked) {
                    // Save current selected chip to manage chip selection/deselection
                    currentSelectedChip = selectedChipText

                    donationSetBookButton.isEnabled = true
                } else {
                    // The same chip is deselected
                    if (currentSelectedChip == selectedChipText) {
                        currentSelectedChip = ""
                        donationSetBookButton.isEnabled = false
                    }
                }
            }

            // Update checked chip based on previously selected chip
            if (selectedInterval == Utility.getPrintableInterval(requireContext(),interval)) {
                chip.isChecked = true
            }
        }
    }

    fun setCloseDialogListener(listener: IDonationBookChooseListener) {
        chipSelectedListener = listener
    }
}