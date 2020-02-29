package com.ppspt.ba.fratresapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ppspt.ba.fratresapp.model.DonationDay

@Dao
interface DonationDayDao {
    @Query("SELECT * FROM donationday")
    fun getAll(): LiveData<List<DonationDay>>

    @Query("SELECT * FROM donationday WHERE day LIKE :day AND month LIKE :month")
    fun findByDay(day: Int, month: Int): LiveData<DonationDay>

    @Insert
    fun insertAll(day: List<DonationDay>)

    @Delete
    fun delete(day: DonationDay)

    @Query("DELETE FROM donationday")
    fun deleteAll()
}