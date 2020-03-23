package com.ppspt.ba.fratresapp.view.donation_info

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.chip.Chip
import com.ppspt.ba.fratresapp.R
import kotlinx.android.synthetic.main.donation_book_dialog.*

class DonationBookDialog : DialogFragment() {
    private lateinit var chipSelectedListener: IDonationBookChooseListener
    private var chipSelectedText = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.donation_book_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val intervals = arrayListOf(
            "08:00 - 08:20",
            "08:20 - 08:40",
            "08:40 - 09:00",
            "09:00 - 09:20",
            "09:20 - 09:40",
            "09:40 - 10:00",
            "10:00 - 10:20",
            "10:20 - 10:40",
            "10:40 - 11:00",
            "11:00 - 11:20",
            "11:20 - 11:40",
            "11:40 - 12:00"
        )
        setBookIntervals(intervals)

        donationBookCloseIcon.setOnClickListener {
            chipSelectedListener.onDonationIntervalSelected(chipSelectedText)
            dismiss()
        }
    }

    private fun setBookIntervals(intervals: ArrayList<String>) {
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
            chip.text = interval

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
            chip.chipStrokeWidth = 2 * requireContext().resources.displayMetrics.density

            // Show the chip icon in chip
            chip.isCloseIconVisible = false
            chip.checkedIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_check_black_24dp)

            donationBookIntervals.addView(chip)

            chip.setOnClickListener {
                chipSelectedText = (it as Chip).text.toString()
            }
        }
    }

    fun setCloseDialogListener(listener: IDonationBookChooseListener) {
        chipSelectedListener = listener
    }
}