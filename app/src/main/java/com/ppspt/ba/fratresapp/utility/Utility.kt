package com.ppspt.ba.fratresapp.utility

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

object Utility {
    fun getLocationFromAddress(context: Context, address: String): LatLng? {
        val coder = Geocoder(context)
        val addressesResult = arrayListOf<Address>()

        try {
            addressesResult.addAll(coder.getFromLocationName(address, 5))

            if (addressesResult.isNullOrEmpty()) {
                return null
            }

            val location = addressesResult[0]

            return LatLng(location.latitude, location.longitude)
        } catch (ioe: IOException) {
            Log.d("UTILITY-GETLOC", ioe.localizedMessage + "\nStacktrace: ${ioe.stackTrace}")
        }

        return null
    }

    /**
     * Add zero char as prefix to [number] and return a formatted string
     */
    fun zeroFormatter(number: Int): String {
        return if (number < 10) {
            "0$number"
        } else {
            "$number"
        }
    }
}