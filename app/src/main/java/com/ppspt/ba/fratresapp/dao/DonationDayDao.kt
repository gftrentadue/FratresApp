package com.ppspt.ba.fratresapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ppspt.ba.fratresapp.model.DonationDay
import com.ppspt.ba.fratresapp.utility.DonationConverters

@Dao
interface DonationDayDao {
    @Query("SELECT * FROM donation_table")
    fun getAll(): LiveData<List<DonationDay>>

    @Query("SELECT * FROM donation_table WHERE day LIKE :day AND month LIKE :month")
    fun findByDay(day: Int, month: Int): LiveData<DonationDay>

    @Query("SELECT * FROM donation_table WHERE ID == :id")
    fun findByID(id: Int): LiveData<DonationDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(day: List<DonationDay>)

    @Update
    fun updateDonationDay(donationDay: DonationDay): Int

    @Delete
    fun delete(day: DonationDay)

    @Query("DELETE FROM donation_table")
    fun deleteAll()
}