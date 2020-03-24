package com.ppspt.ba.fratresapp.model

import androidx.room.Entity

const val USER_TABLE_NAME: String = "UserTable"
const val NAME_COLUMN_NAME: String = "NAME"
const val SURNAME_COLUMN_NAME: String = "SURNAME"
const val ROUTE_COLUMN_NAME: String = "ROUTE"
const val CITY_COLUMN_NAME: String = "CITY"
const val PROVINCE_COLUMN_NAME: String = "PROVINCE"
const val DATE_BIRTH_COLUMN_NAME: String = "DATE_BIRTH"

@Entity
data class User(
    val name: String,
    val surname: String,
    val route: String,
    val city: String,
    val cap: String,
    val province: String,
    val dateOfBirth: Long?
){
    fun getAddress(): String{
        return "$route, $cap $city $province"
    }
}