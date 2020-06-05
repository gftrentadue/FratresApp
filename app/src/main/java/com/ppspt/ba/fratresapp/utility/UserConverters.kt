package com.ppspt.ba.fratresapp.utility

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ppspt.ba.fratresapp.model.User

object UserConverters {
    @TypeConverter
    fun userListToJson(list: ArrayList<User>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun jsonToUserList(json: String): ArrayList<User> {
        val listType = object : TypeToken<ArrayList<User>>() {}.type
        return Gson().fromJson(json, listType)
    }
}