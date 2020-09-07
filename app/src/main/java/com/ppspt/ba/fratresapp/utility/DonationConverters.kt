package com.ppspt.ba.fratresapp.utility

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.model.DonationInterval

class DonationConverters {
    @TypeConverter
    fun donationListToJson(list: List<DonationDay>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun jsonToDonationList(json: String): List<DonationDay> {
        val listType = object : TypeToken<List<DonationDay>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun donationIntervalToJson(list: ArrayList<DonationInterval>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun jsonToDonationIntervals(json: String): ArrayList<DonationInterval> {
        val listType = object : TypeToken<ArrayList<DonationInterval>>() {}.type
        return Gson().fromJson(json, listType)
    }
}