package com.ppspt.ba.fratresapp.domain.donation_calendar.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ppspt.ba.fratresapp.R
import com.ppspt.ba.fratresapp.model.Day
import java.util.*

class CalendarDayAdapter(
    private val context: Context,
    items: ArrayList<Day>,
    private val clickListener: (position: Int) -> Unit
) :
    RecyclerView.Adapter<CalendarDayAdapter.CalendarDay>() {

    private val layoutID = R.layout.donation_calendar_day_layout
    private val dayList = arrayListOf<Day>()
    private var currentMonth: Int? = null
    private var currentYear: Int? = null

    init {
        dayList.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDay {
        val view = LayoutInflater.from(context).inflate(layoutID, parent, false)

        return CalendarDay(view, clickListener)
    }

    override fun getItemCount(): Int = dayList.size

    override fun onBindViewHolder(holder: CalendarDay, position: Int) {
        val elemToBind = dayList[position]

        holder.bind(elemToBind, currentMonth, currentYear)
    }

    fun setDaysMonth(list: ArrayList<Day>, newMonth: Int, newYear: Int) {
        dayList.clear()
        dayList.addAll(list)

        currentMonth = newMonth
        currentYear = newYear

        notifyDataSetChanged()
    }

    class CalendarDay(itemView: View, private val clickListener: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val dayTextLayout: ConstraintLayout =
            itemView.findViewById(R.id.calendarDayTextLayout)
        private val dayTextView: AppCompatTextView = itemView.findViewById(R.id.calendarDayTextView)
        private val dayImageView: AppCompatImageView =
            itemView.findViewById(R.id.calendarDayImageView)
        private val dayMainLayout: ConstraintLayout =
            itemView.findViewById(R.id.calendarDayMainLayout)
        private val calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
        private val today = calendar.get(Calendar.DAY_OF_MONTH)
        private val month = calendar.get(Calendar.MONTH)
        private val year = calendar.get(Calendar.YEAR)

        fun bind(day: Day, currentMonth: Int?, currentYear: Int?) {
            var donationDayInCurrentMonth = true

            calendar.time = day.date

            if (currentYear ?: year != calendar.get(Calendar.YEAR) || currentMonth ?: month != calendar.get(
                    Calendar.MONTH
                )
            ) {
                dayTextView.setTextColor(Color.GRAY)
                donationDayInCurrentMonth = false
            } else if (today == calendar.get(Calendar.DAY_OF_MONTH) && month == calendar.get(
                    Calendar.MONTH
                )
            ) {
                dayTextView.setTextColor(Color.WHITE)
                dayTextLayout.setBackgroundResource(R.drawable.calendar_today_background)
            }

            // Check if is a donation day
            if (donationDayInCurrentMonth) {
                if (day.donationID != null) {
                    dayImageView.visibility = View.VISIBLE

                    dayMainLayout.setOnClickListener {
                        clickListener.invoke(day.donationID!!)
                    }
                } else {
                    dayImageView.visibility = View.INVISIBLE
                    dayMainLayout.setOnClickListener {
                        clickListener.invoke(-1)
                    }
                }
            } else {
                dayImageView.visibility = View.INVISIBLE
                dayMainLayout.setOnClickListener {
                    clickListener.invoke(-1)
                }
            }

            val dayToBind = calendar.get(Calendar.DAY_OF_MONTH)
            dayTextView.text = dayToBind.toString()
        }
    }
}