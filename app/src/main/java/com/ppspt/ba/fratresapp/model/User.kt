package com.ppspt.ba.fratresapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity

const val USER_TABLE_NAME = "user_table"
const val USER_NAME_COLUMN_NAME = "NAME"
const val USER_NAME_COLUMN_SURNAME = "SURNAME"
const val USER_NAME_COLUMN_ROUTE = "ROUTE"
const val USER_NAME_COLUMN_CITY = "CITY"
const val USER_NAME_COLUMN_CAP = "CAP"
const val USER_NAME_COLUMN_PROVINCE = "PROVINCE"
const val USER_NAME_COLUMN_DATE = "DATEOFBIRTH"

@Entity(tableName = USER_TABLE_NAME, primaryKeys = arrayOf("NAME", "SURNAME", "DATEOFBIRTH"))
class User(
    @ColumnInfo(name = USER_NAME_COLUMN_NAME) val name: String = "",
    @ColumnInfo(name = USER_NAME_COLUMN_SURNAME) val surname: String = "",
    @ColumnInfo(name = USER_NAME_COLUMN_ROUTE) val route: String = "",
    @ColumnInfo(name = USER_NAME_COLUMN_CITY) val city: String = "",
    @ColumnInfo(name = USER_NAME_COLUMN_CAP) val cap: String = "",
    @ColumnInfo(name = USER_NAME_COLUMN_PROVINCE) val province: String = "",
    @ColumnInfo(name = USER_NAME_COLUMN_DATE) val dateOfBirth: Long = 0L
) : Comparable<User> {

    constructor() : this("", "", "", "", "", "", 0)

    fun getAddress(): String {
        return "$route, $cap $city $province"
    }

    override fun compareTo(other: User): Int =
        compareValuesBy(this, other,
            { u -> u.name },
            { u -> u.surname },
            { u -> u.dateOfBirth })
}