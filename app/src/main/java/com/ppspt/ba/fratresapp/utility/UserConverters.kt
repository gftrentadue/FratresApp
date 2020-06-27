package com.ppspt.ba.fratresapp.utility

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ppspt.ba.fratresapp.model.User

class UserConverters {
    @TypeConverter
    fun userListToJson(list: List<User>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun jsonToUserList(json: String): List<User> {
        val listType = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson(json, listType)
    }
}